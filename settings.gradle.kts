enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Floodgate, Cumulus etc.
        maven("https://repo.opencollab.dev/main")

        mavenCentral()
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
//    includeBuild("build-logic")
}

rootProject.name = "api-parent"

include(":base")
include(":geyser")