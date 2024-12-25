pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven {
            url = uri("https://jitpack.io")
        }
        mavenCentral()
    }
}

rootProject.name = "FILMUS"
include(":app")
include(":feature-movie-api")
include(":feature-movie-impl")
include(":feature-movie-database")
include(":feature-movie-ui")
include(":feature-filmus-movie-api")
include(":feature-filmus-movie-impl")
