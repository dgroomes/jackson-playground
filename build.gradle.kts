import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.12.0-SNAPSHOT" // these artifacts must be built from source. see the README.md
val junitPlatformVersion = "1.7.0" // releases: https://github.com/junit-team/junit5/releases
val junitJupiterVersion = "5.7.0"

java {
    sourceCompatibility = JavaVersion.VERSION_15
}

/**
 * Configure the compiler step to accommodate:
 * - Java language preview features
 * - Preserve parameter names in the bytecode to enable Jackson to deserialize using constructors
 */
tasks {
    withType(JavaCompile::class.java) {
        options.compilerArgs.addAll(arrayOf("--enable-preview", "-parameters"))
    }

    withType(Test::class.java) {
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
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonVersion"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}
