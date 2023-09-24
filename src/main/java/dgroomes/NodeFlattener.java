package dgroomes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Utility to flatten Jackson {@link ObjectNode} instances into flattened versions of themselves.
 * <p>
 * A similar problem statement is described in a StackOverflow question: https://stackoverflow.com/q/20355261
 */
public class NodeFlattener {

    /**
     * Flatten a Jackson {@link ObjectNode} to a new {@link ObjectNode} that's flattened. In other words, the new node
     * has no nested values.
     * <p>
     * For example, given an {@link ObjectNode} with two top-level entries, where one value is itself an object:
     * <pre>
     * {
     *   "type": "point",
     *   "value": {
     *     "x": 1,
     *     "y": 2
     *   }
     * }
     * </pre>
     * It will flatten into an {@link ObjectNode} containing three top-level entries whose values are simple types (non-objects):
     * <p>
     * <pre>
     * {
     *   "type": "point",
     *   "value.x": 1,
     *   "value.y": 2
     * }
     * </pre>
     * <p>
     * WARNING: This method does not support list types.
     * <p>
     *
     * @param nestedObjectNode an {@link ObjectNode} with nested values
     * @return a flattened {@link ObjectNode}. It contains no nested values. Every entry is a simple type like a number or a string.
     * @throws IllegalArgumentException if the nestedObjectNode contains any list types
     */
    public static ObjectNode flatten(ObjectNode nestedObjectNode) {
        ObjectNode flattenedNode = JsonNodeFactory.instance.objectNode();

        var nodeStack = new Stack<PathedNode>();
        nodeStack.push(new PathedNode(Collections.emptyList(), nestedObjectNode));

        while (!nodeStack.isEmpty()) {
            var node = nodeStack.pop();
            switch (node.jsonNode) {

                // Start with the easy case first. If the node is a value node, we've reached a dead-end, or a "leaf
                // node". We add its value to the new flattened object node. We can move on to processing the next
                // "head" node in the stack.
                case ValueNode valueNode -> flattenedNode.set(node.path(), valueNode);

                // We're not handling list types, just to make things easy for this demo.
                case ArrayNode ignored -> throw new IllegalArgumentException("List types are not supported for flattening");
                // I'm not sure a null value is even possible... but I'm going to handle it with a meaningful error message anyway.
                case null -> throw new IllegalStateException("Expected a non-null node value but found null");

                // Finally, handle the object type which requires us to push nodes on the stack.
                case ObjectNode objectNode -> objectNode.fields().forEachRemaining(entry -> {
                    var childNode = node.child(entry.getKey(), entry.getValue());
                    nodeStack.push(childNode);
                });

                default -> throw new IllegalStateException("Unexpected JsonNode type: %s".formatted(node.jsonNode.getClass()));
            }
        }

        return flattenedNode;
    }

    /**
     * A helper data structure that keeps track of the path that points to the given {@link JsonNode}.
     */
    private record PathedNode(List<String> pathComponents, JsonNode jsonNode) {

        PathedNode {
            // Defensive copy
            pathComponents = List.copyOf(pathComponents);
        }

        /**
         * Get a dot-limited representation of the path.
         * <p>
         * For example, "value.x"
         */
        public String path() {
            return String.join(".", pathComponents);
        }

        /**
         * Create a new child node that extends from this node. The child node's path components will be an extended
         * version of its parent's path. Specifically, the child path is the parent path plus "fieldName".
         */
        public PathedNode child(String fieldName, JsonNode jsonNode) {
            var extendedPath = new ArrayList<>(pathComponents);
            extendedPath.add(fieldName);
            return new PathedNode(extendedPath, jsonNode);
        }
    }
}
