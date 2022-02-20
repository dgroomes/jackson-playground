package dgroomes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NodeFlattenerTest extends BaseTest {

    /**
     * Flattening JSON. This test uses the {@link NodeFlattener} class's utility method to flatten a nested Jackson
     * {@link ObjectNode} into a flat {@link ObjectNode}.
     */
    @Test
    void flatten() throws Exception {
        String json = """
                {
                  "type": "point",
                  "value": {
                    "x": 1,
                    "y": 2
                  }
                }""";
        var objectNode = ((ObjectNode) mapper.readTree(json));

        ObjectNode flattenedNode = NodeFlattener.flatten(objectNode);

        assertThat(flattenedNode).hasSize(3);
        assertContainsStringEntry(flattenedNode, "type", "point");
        assertContainsNumberEntry(flattenedNode, "value.x", 1);
        assertContainsNumberEntry(flattenedNode, "value.y", 2);
    }

    private static JsonNode getAndAssertContainsKey(ObjectNode objectNode, String key) {
        JsonNode foundValue = objectNode.get(key);
        assertThat(foundValue)
                .describedAs("The ObjectNode should have an entry for key '%s'", key)
                .isNotNull();
        return foundValue;
    }

    /**
     * Assert that an {@link ObjectNode} contains a key with the given string value.
     *
     * @param objectNode    the object-under-test
     * @param key           the key of the entry to look for
     * @param expectedValue the expected value for the entry
     */
    public static void assertContainsStringEntry(ObjectNode objectNode, String key, String expectedValue) {
        JsonNode foundValue = getAndAssertContainsKey(objectNode, key);
        assertThat(foundValue.textValue()).isEqualTo(expectedValue);
    }

    /**
     * Assert that an {@link ObjectNode} contains a key with the given number value.
     *
     * @param objectNode    the object-under-test
     * @param key           the key of the entry to look for
     * @param expectedValue the expected value for the entry
     */
    public static void assertContainsNumberEntry(ObjectNode objectNode, String key, Number expectedValue) {
        JsonNode foundValue = getAndAssertContainsKey(objectNode, key);

        assertThat(foundValue.numberValue()).isEqualTo(expectedValue);
    }
}
