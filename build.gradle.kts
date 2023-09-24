import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

val slf4jVersion = "2.0.9" // SLF4J releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.16.0-SNAPSHOT" // These artifacts must be built from source. See the README.md
val junitPlatformVersion = "1.10.0" // JUnit 5 releases: https://github.com/junit-team/junit5/releases
val junitJupiterVersion = "5.10.0"
val assertJVersion = "3.24.2" // AssertJ releases: https://github.com/assertj/assertj-core/tags

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

/**
 * Customize the tasks. Specifically, configure the compiler step to preserve parameter names in the bytecode to enable
 * Jackson to deserialize using constructors. Also, enable Java language "Preview Features".
 */
tasks {
    withType(JavaCompile::class.java) {
        options.compilerArgs.addAll(arrayOf("-parameters", "--enable-preview"))
    }

    test {
        useJUnitPlatform()
        jvmArgs = listOf("--enable-preview")
        testLogging {
            showStandardStreams = true
            events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

repositories {
    // We purposely want to include 'mavenLocal' because we are depending on locally-built Jackson artifacts (.jar files).
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
    testImplementation("org.assertj:assertj-core:$assertJVersion")
}
