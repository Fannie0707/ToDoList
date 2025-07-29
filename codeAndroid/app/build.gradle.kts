plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) // ✅ ajoute ce plugin
}

android {
    namespace = "com.example.todolist"
    compileSdk = 34 // ✅ 36 n'existe pas encore (max = 34 en juillet 2025)

    defaultConfig {
        applicationId = "com.example.todolist"
        minSdk = 24
        targetSdk = 34 // ✅ idem, 36 est invalide actuellement
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
}

dependencies {
    implementation(libs.gson)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.okhttp3.okhttp)
    implementation(libs.material_calendarview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
