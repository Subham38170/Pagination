plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.pagination"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pagination"
        minSdk = 26
        targetSdk = 36
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Room database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)  // For annotation processing
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson) // Gson converter for Retrofit

    // Core Paging library (runtime)
    implementation(libs.androidx.paging.runtime.ktx)

    // Optional - For Jetpack Compose integration
    implementation(libs.androidx.paging.compose)


    //Dagger -hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation(libs.androidx.hilt.navigation.compose) // Or latest

    //Coil


// Jetpack Compose support with AsyncImage
    implementation("io.coil-kt:coil-compose:2.6.0")





}