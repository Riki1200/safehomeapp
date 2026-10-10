/*
 *
 *  *
 *  *  * Copyright (c) 2026
 *  *  *
 *  *  * Author: Athar Gul
 *  *  * GitHub: https://github.com/DevAtrii/Kmp-Starter-Template
 *  *  * YouTube: https://www.youtube.com/@devatrii/videos
 *  *  *
 *  *  * All rights reserved.
 *  *
 *  *
 *
 */

rootProject.name = "SafeHome"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        val starterLibsDir = rootDir.resolve(".starter-libs")
        if (starterLibsDir.exists()) {
            maven(starterLibsDir.toURI()) {
                name = "starterLibsLocal"
                content {
                    includeGroup("io.github.devatrii")
                }
            }
        }
    }
}

includeBuild("build-logic")
include(":composeApp")
include(":starter:core")
include(":starter:utils")
include(":starter:native:bindings")
include(":starter:ui:utils")
include(":starter:ui:components")
include(":starter:ui:layouts")
include(":androidApp")
include(":features:navigation")
include(":features:core:domain")
include(":features:core:data")
include(":features:core:presentation")
include(":features:remote_config:domain")
include(":features:remote_config:data")
include(":features:remote_config:presentation")
include(":features:resources")
include(":features:notifications:core")
include(":features:notifications:local")
include(":features:notifications:push")
include(":features:analytics:domain")
include(":features:analytics:data")
include(":features:database")
include(":features:purchases:data")
include(":features:purchases:domain")
include(":features:purchases:presentation")
include(":features:locale")
include(":features:analytics:data-firebase")
include(":features:auth:domain")
include(":features:auth:data")
include(":features:auth:presentation")

include(":features:verification:domain")
include(":features:verification:data")
include(":features:verification:presentation")

include(":features:map:domain")
include(":features:map:data")
include(":features:map:presentation")

include(":features:appointments:domain")
include(":features:appointments:data")
include(":features:appointments:presentation")

include(":features:chat:domain")
include(":features:chat:data")
include(":features:chat:presentation")

include(":features:assistant:domain")
include(":features:assistant:data")
include(":features:assistant:presentation")

include(":features:owner:domain")
include(":features:owner:data")
include(":features:owner:presentation")

include(":features:profile:domain")
include(":features:profile:data")
include(":features:profile:presentation")

include(":features:home:presentation")
include(":features:home:domain")
include(":features:home:data")