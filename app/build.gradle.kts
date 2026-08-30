plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.recipeapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization { enable = false }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    // kotlinOptions removed as part of AGP 9.0 built-in Kotlin migration
    buildFeatures {
        compose = true
        viewBinding = true               // required for all fragment ViewBindings
    }
}

dependencies {
    // ── Existing team dependencies (unchanged) ────────────────────────────
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    val fragment_version = "1.9.0"
    implementation("androidx.fragment:fragment-ktx:${fragment_version}")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.airbnb.android:lottie:6.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

    // ── Task 4 additions ──────────────────────────────────────────────────
    // Navigation Component (nav graph + findNavController)
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.0")
    // Room (DAO contract; Person 1 provides @Database)
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")
    // LiveData coroutine extensions
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    // ConstraintLayout for detail / favorite / overlay layouts
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    // Glide — image loading for recipe thumbnails
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // YouTube player overlay
    implementation(libs.youtube.player)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    // okHTTP

    implementation("com.squareup.okhttp3:okhttp:5.5.0")

    implementation("com.squareup.okhttp3:logging-interceptor:5.5.0")


}
