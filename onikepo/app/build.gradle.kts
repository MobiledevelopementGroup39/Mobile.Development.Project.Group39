plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.foodlearningapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodlearningapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)

    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.ui:ui:1.6.4") // Use the latest version
    implementation("androidx.compose.material:material:1.6.4") // Use the latest version
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.4") // Use the latest version
    implementation("androidx.activity:activity-compose:1.8.2") // Use the latest version
    implementation("androidx.navigation:navigation-compose:2.7.6") // Use the latest version for navigation
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Use the latest version
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Use the same version as Retrofit
    implementation("com.squareup.okhttp3:okhttp:4.9.3") //
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.media3:media3-exoplayer:1.1.0")
    implementation("androidx.media3:media3-ui:1.1.0")
    implementation("androidx.compose.runtime:runtime-livedata")

    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation(libs.generativeai)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}