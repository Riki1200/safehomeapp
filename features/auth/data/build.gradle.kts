plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    // from build logic
    alias(libs.plugins.kotlin.serialization)
    id(libs.plugins.build.koin.core.get().pluginId)
    id(libs.plugins.build.common.get().pluginId)
}

kotlin {
    android {
        namespace = "com.romeodev.safehomeapp.feature_auth_data"
        compileSdk {
            version = release(version = libs.versions.android.compileSdk.get().toInt())
        }
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
    }

    val xcfName = "starter:featureAuthFeatureDataKit"

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.features.core.data)
                api(projects.features.auth.domain)
            }
        }

        androidMain {
            dependencies {
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}
