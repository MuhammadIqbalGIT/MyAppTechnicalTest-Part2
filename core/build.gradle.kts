plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}



android {
    namespace = "com.example.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 25
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    flavorDimensions.add("env")

    productFlavors {

        create("dev") {
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://api.chucknorris.io/\"")
        }

        create("prod") {
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://api.chucknorris.io/\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}