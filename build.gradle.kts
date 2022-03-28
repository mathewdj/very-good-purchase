import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
	id("org.springframework.boot") version "2.6.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("java")
	id("org.jetbrains.kotlin.jvm") version "1.6.10"
	id("com.google.cloud.tools.jib") version "3.1.4"
	id( "org.openapi.generator") version "5.3.0"
}

group = "green.seagull"
version = "0.2.0"

repositories {
	mavenCentral()
}


val integrationTest: SourceSet by sourceSets.creating

configurations[integrationTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
configurations[integrationTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

val integrationTestTask = tasks.register<Test>("integrationTest") {
	description = "Runs integration tests."
	group = "verification"
	useJUnitPlatform()

	testLogging {
		events = mutableSetOf(PASSED, SKIPPED, FAILED)
	}

	testClassesDirs = integrationTest.output.classesDirs
	classpath = configurations[integrationTest.runtimeClasspathConfigurationName] + integrationTest.output

	shouldRunAfter(tasks.test)
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

	implementation("com.squareup.moshi:moshi-kotlin:1.12.0") // Required by org.openapi.generator

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.assertj:assertj-core:3.21.0")
	testImplementation("org.mockito:mockito-core:4.1.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

	"integrationTestImplementation"(project)
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

sourceSets.main {
	java.srcDirs("build/generated/src/main/kotlin")
}

openApiValidate {
	inputSpec.set("$rootDir/src/main/resources/schema/purchase-api.yml")
	recommend.set(true)
}

openApiGenerate {
	generatorName.set("kotlin")
	inputSpec.set("$rootDir/src/main/resources/schema/purchase-api.yml")
	outputDir.set("$buildDir/generated")
	modelPackage.set("green.seagull.very.good.purchase.model")
	modelFilesConstrainedTo.set(listOf("Purchase", "PurchaseType"))
	configOptions.set(mapOf("dateLibrary" to "java8"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	dependsOn("openApiGenerate")
}
