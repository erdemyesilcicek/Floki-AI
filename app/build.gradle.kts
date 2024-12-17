plugins {
    id("com.google.gms.google-services")

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.erdemyesilcicek.flokiai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.erdemyesilcicek.flokiai"
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
            //val geminiApiKey = project.findProperty("GEMINI_API_KEY") as String? ?: ""
            //buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        /*
        debug {
            // Gradle properties'ten API anahtarını al
            val geminiApiKey = project.findProperty("GEMINI_API_KEY") as String? ?: ""
            buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")
        }

         */
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
        //buildFeatures.buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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

    //Gemini
    implementation("com.google.ai.client.generativeai:generativeai:0.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")

    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.5")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.4")

    //Lottie Animation
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    //SplashApi
    implementation(libs.androidx.core.splashscreen)

}