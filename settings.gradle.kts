pluginManagement {
	repositories {
		maven {
			name = "Quilt"
			url = uri("https://maven.quiltmc.org/repository/release")
		}
		// Currently needed for Intermediary and other temporary dependencies
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}

		gradlePluginPortal()
		mavenCentral()
	}

	val kotlin: String by settings
	val loom: String by settings
	plugins {
		kotlin("jvm") version kotlin apply false
		id("org.quiltmc.loom") version loom apply false
	}
}

rootProject.name = "YYII"
