import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.1.1"
    id("jacoco")
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
}

group = "com.microservice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // datasource
    implementation("org.postgresql:postgresql:42.2.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.data:spring-data-redis")
//    implementation("redis.clients:jedis")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.github.microutils:kotlin-logging:1.7.6")

    // DevOps
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.assertj:assertj-core:3.14.0")
}

detekt {
    toolVersion = "1.1.1"
    input = files("$projectDir/src/main/kotlin")
    config = files("$projectDir/src/main/resources/stylecheck.yml")
}

jacoco {
    toolVersion = "0.8.2"
    reportsDir = file("$buildDir/jacocoReport")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Detekt> {
    exclude(".*/resources/.*", ".*/build/.*")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register("download") {
    configurations.testCompile.get().files
    println("Downloaded all dependencies")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "CLASS"

            limit {
                excludes = listOf("**Application*", "**config.*", "**dto*", "**entity*", "**resource*", "**service*", "**repository*")
                counter = "INSTRUCTION"
                value = "COVEREDRATIO"
                minimum = BigDecimal(0.85)
            }
        }
    }
}

tasks.build {
    System.out.println("build.")
    finalizedBy(tasks.jacocoTestCoverageVerification, tasks.jacocoTestReport)
}

tasks.bootRun {
    val env = System.getenv("APP_ENV") ?: "local"
    System.out.println(env)
    systemProperty("spring.profiles.active", env)
}
