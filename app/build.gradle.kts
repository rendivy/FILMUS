plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0"
    id("kotlin-kapt")
}

android {
    namespace = "com.example.cinema_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cinema_app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val coreKtxVersion = "1.12.0"
    val hiltVersion = "2.48"
    val navVersion = "2.7.4"
    val kotlinSerializationVersion = "1.6.0"
    val retrofitVersion = "2.9.0"
    val kotlinSerialization = "1.0.0"
    val okHttpVersion = "4.11.0"
    val material3Version = "1.1.1"
    val activityComposeVersion = "1.8.0"
    val securityCryptoVersion = "1.1.0-alpha06"
    val lifecycleVersion = "2.6.2"

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.security:security-crypto:$securityCryptoVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$kotlinSerialization")
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha10")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0-alpha10")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("junit:junit:4.13.2")

    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
}