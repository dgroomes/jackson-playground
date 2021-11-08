import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

val slf4jVersion = "1.7.32" // SLF4J releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.14.0-SNAPSHOT" // These artifacts must be built from source. See the README.md
val junitPlatformVersion = "1.8.1" // JUnit 5 releases: https://github.com/junit-team/junit5/releases
val junitJupiterVersion = "5.8.1"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

/**
 * Configure the compiler step to accommodate:
 * - Preserve parameter names in the bytecode to enable Jackson to deserialize using constructors
 */
tasks {
    withType(JavaCompile::class.java) {
        options.compilerArgs.addAll(arrayOf("-parameters"))
    }

    test {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
            events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonVersion"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}
