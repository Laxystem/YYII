import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	`maven-publish`
	kotlin("jvm")
	id("org.quiltmc.loom")
}

val namespace: String by project
base.archivesName.set(namespace)

val javaVersion = 17

repositories {
	maven("https://maven.nucleoid.xyz/") {
		name = "Fantasy"
	}
}

val minecraft: String by project
val mappings: String by project
val loader: String by project
val qsl: String by project
val qkl: String by project
val mixinextras: String by project
val fantasy: String by project
val kotlin: String by project

// All the dependencies are declared at gradle/libs.version.toml and referenced with "libs.<id>"
// See https://docs.gradle.org/current/userguide/platforms.html for information on how version catalogs work.
dependencies {
	minecraft("com.mojang:minecraft:$minecraft")

	@Suppress("UnstableApiUsage")
	mappings(
		loom.layered {
			officialMojangMappings()
			mappings("org.quiltmc:quilt-mappings:$minecraft+build.$mappings:intermediary-v2")
		}
	)

	modImplementation("org.quiltmc:quilt-loader:$loader")

	modImplementation("org.quiltmc.quilted-fabric-api:quilted-fabric-api:$qsl-$minecraft")
	modImplementation("org.quiltmc.quilt-kotlin-libraries:quilt-kotlin-libraries:${qkl.replace(oldValue = "+", newValue = "+kt.$kotlin+")}")

	include(implementation(annotationProcessor("io.github.llamalad7:mixinextras-fabric:$mixinextras")!!)!!)

	modImplementation("xyz.nucleoid:fantasy:$fantasy") {
		exclude(group = "net.fabricmc")
	}

	testImplementation(kotlin("test"))
}

loom {
	mods {
		val yyii by creating { }
	}
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			jvmTarget = javaVersion.toString()
			// languageVersion: A.B of the kotlin plugin version A.B.C
			languageVersion = kotlin.substringBeforeLast('.')
		}
	}

	withType<JavaCompile>().configureEach {
		options.encoding = "UTF-8"
		options.isDeprecation = true
		options.release.set(javaVersion)
	}

	processResources {
		filteringCharset = "UTF-8"
		inputs.property("version", project.version)
		inputs.property("namespace", namespace)

		filesMatching("quilt.mod.json") {
			expand(
				mapOf(
					"version" to project.version,
					"namespace" to namespace
				)
			)
		}
	}

	javadoc {
		options.encoding = "UTF-8"
	}

	// Run `./gradlew wrapper --gradle-version <newVersion>` or `gradle wrapper --gradle-version <newVersion>` to update gradle scripts
	// BIN distribution should be sufficient for the majority of mods
	wrapper {
		distributionType = Wrapper.DistributionType.BIN
	}

	jar {
		from("LICENSE.md") {
			rename { "LICENSE_${namespace}.md" }
		}
	}

	test {
		useJUnitPlatform()
	}
}

val targetJavaVersion = JavaVersion.toVersion(javaVersion)
if (JavaVersion.current() < targetJavaVersion) {
	project.kotlin.jvmToolchain(javaVersion)

	java.toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

	// If this mod is going to be a library, then it should also generate Javadocs in order to aid with development.
	// Uncomment this line to generate them.
	// withJavadocJar()

	// Still required by IDEs such as Eclipse and VSC
	sourceCompatibility = targetJavaVersion
	targetCompatibility = targetJavaVersion
}

// Configure the maven publication
publishing {
	publications {
		register<MavenPublication>("Maven") {
			from(components.getByName("java"))
		}
	}

	// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	repositories {
		// Add repositories to publish to here.
		// Notice: This block does NOT have the same function as the block in the top level.
		// The repositories here will be used for publishing your artifact, not for
		// retrieving dependencies.
	}
}
