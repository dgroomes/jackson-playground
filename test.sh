#!/usr/bin/env bash
# Run the tests using the Standalone JUnit Console Launcher and use Java 14.
# We must use this script to run the tests because the code is compiled for Java 14 but Gradle does not support
# executing in Java 14.

set -eu

./gradlew compileTestJava printJunitLauncherPath printTestClassPath

LAUNCHER_PATH_FILE=build/junit-launcher-path.txt
TEST_CLASSPATH_FILE=build/test-classpath.txt
JAVA_14_BIN="$JAVA_14_HOME/bin/java"

assertFileExists() {
  local file=$1
  if [[ ! -f "$file" ]]; then
    echo >&2 "Expected to find file '$file' but did not"
    exit 1
  fi
}

assertJavaVersion() {
  # `java --full-version` will print one line to standard out containing a prefix and the "Version String". See https://openjdk.java.net/jeps/223
  # There's a regular expression in the document, but it is not practical to use. Instead, we are simply interested in
  # the Java MAJOR version. Java releases follow the MAJOR.MINOR.SECURITY pattern EXCEPT for early access releases which
  # include only the MAJOR version and a postamble like "-ea+28-1366". So we will relatively safely just try to match the
  # first uninterrupted string of digits we find and take that as the MAJOR version.
  VERSION_OUTPUT=$("$JAVA_14_BIN" --full-version)
  if [[ $VERSION_OUTPUT =~ ([0-9]+) ]]; then
    local major=${BASH_REMATCH[1]}
    if [[ $major != 14 ]]; then
      echo >&2 "Requires Java 14 but found Java $major"
      exit 1
    fi
  else
    echo >&2 "Did not recognize a version string from the 'java --full-version' output: $VERSION_OUTPUT"
    exit 1
  fi
}

assertFileExists "$LAUNCHER_PATH_FILE"
assertFileExists "$TEST_CLASSPATH_FILE"
assertFileExists "$JAVA_14_BIN"
assertJavaVersion

LAUNCHER_PATH=$(cat "$LAUNCHER_PATH_FILE")

"$JAVA_14_BIN" --enable-preview -jar "$LAUNCHER_PATH" \
  --include-engine junit-jupiter \
  --fail-if-no-tests \
  --scan-classpath \
  --classpath @$TEST_CLASSPATH_FILE
