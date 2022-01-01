import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("java")
	id("org.jetbrains.kotlin.jvm") version "1.6.10"
	id("com.google.cloud.tools.jib") version "3.1.4"
}

group = "green.seagull"
version = "0.2.0"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.assertj:assertj-core:3.21.0")
	testImplementation("org.mockito:mockito-core:4.1.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

}

tasks.named<Test>("test") {
	useJUnitPlatform()

	testLogging {
		events = mutableSetOf(PASSED, SKIPPED, FAILED)
	}
}

jib {
	from {
		image = "arm64v8/openjdk"
		platforms {
			platform {
				architecture = "arm64"
				os = "linux"
			}
		}
	}
	to {
		image = "berrydockerregistry:5000/very-good-purchase:$version" // Local test registry
	}
	setAllowInsecureRegistries(true)
	container {
		ports = listOf("8080")
	}
}
