plugins {
    id("com.android.application") version "8.2.2"
    id("org.jetbrains.kotlin.android") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}



android {
    namespace = "com.example.recipeasy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recipeasy"
        minSdk = 24
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
        viewBinding = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.7")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.camera:camera-view:1.3.3")
    implementation("androidx.camera:camera-extensions:1.3.3")
    implementation("androidx.work:work-testing:2.9.0")
    implementation("com.google.android.gms:play-services-basement:18.3.0")
    val nav_version = "2.7.7"
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6")
    implementation("androidx.compose.material3:material3:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.0")
    androidTestImplementation("org.mockito:mockito-android:5.12.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.6")

    // Room
    implementation("androidx.room:room-runtime:2.5.2}")
    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    //implementation("androidx.room:room-runtime:2.6.1")
    //ksp("androidx.room:room-compiler:2.6.1")
    //implementation("androidx.room:room-ktx:2.6.1")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.compose.runtime:runtime:1.6.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")

    // Images from url
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")


    testImplementation ("org.robolectric:robolectric:4.5.1")

}