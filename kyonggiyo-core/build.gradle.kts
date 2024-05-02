import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false

dependencies {
    // Spring
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations")

    // IdGenerator
    implementation("com.github.f4b6a3:tsid-creator:5.2.4")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // JSON
    implementation("org.json:json:20230618")

    // Test Fixture
    api("org.instancio:instancio-junit:3.0.0")
    api("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.16")
}
