#!/usr/bin/env bash
# Build the "edge versions" of the Jackson artifacts.
#
# The source code for a number of Jackson projects is included in this repository as git submodules. This script
# iterates through each of these submodules and executes a Maven "install".

JACKSON_VERSION=2.14
REPOS=(
  jackson-bom
  jackson-core
  jackson-annotations
  jackson-databind
  jackson-modules-java8
  jackson-dataformats-text
)

for repo in "${REPOS[@]}"; do
  git -C "$repo" checkout "$JACKSON_VERSION"
  git -C "$repo" pull
  mvn --file "$repo"/pom.xml install -Dmaven.test.skip=true
done
