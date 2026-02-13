import java.util.Properties

plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.pocketmonsters"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pocketmonsters"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { localProperties.load(it) }
        }

        val apiUrl = localProperties.getProperty("api.url") ?: throw GradleException("api.url not found in local.properties")
        buildConfigField("String", "API_URL", "\"$apiUrl\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true  // Add this line
    }
    buildToolsVersion = "36.0.0"

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
        // Version definitions
        val appcompatVersion = "1.7.1"
        val constraintlayoutVersion = "2.2.1"
        val legacySupportVersion = "1.0.0"
        val recyclerviewVersion = "1.4.0"
        val lifecycleVersion = "2.10.0"
        val materialVersion = "1.13.0"
        val playServicesMapsVersion = "20.0.0"
        val playServicesLocationVersion = "21.3.0"
        val firebaseCrashlyticsVersion = "20.0.4"
        val firebaseAnalyticsVersion = "23.0.0"
        val retrofitVersion = "3.0.0"
        val okhttpVersion = "5.3.2"
        val roomVersion = "2.8.4"
        val guavaVersion = "33.5.0-android"
        val junitVersion = "4.13.2"
        val androidxJunitVersion = "1.3.0"
        val espressoVersion = "3.7.0"

        // Core AndroidX libraries
        implementation("androidx.appcompat:appcompat:$appcompatVersion")
        implementation("androidx.constraintlayout:constraintlayout:$constraintlayoutVersion")
        implementation("androidx.legacy:legacy-support-v4:$legacySupportVersion")
        implementation("androidx.recyclerview:recyclerview:$recyclerviewVersion")

        // Lifecycle components
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

        // Material Design
        implementation("com.google.android.material:material:$materialVersion")

        // Google Play Services
        implementation("com.google.android.gms:play-services-maps:$playServicesMapsVersion")
        implementation("com.google.android.gms:play-services-location:$playServicesLocationVersion")

        // Firebase
        implementation("com.google.firebase:firebase-crashlytics:$firebaseCrashlyticsVersion")
        implementation("com.google.firebase:firebase-analytics:$firebaseAnalyticsVersion")

        // Retrofit for API calls
        implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
        implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
        implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")

        // Room for local database
        implementation("androidx.room:room-runtime:$roomVersion")
        implementation("androidx.room:room-guava:$roomVersion")
        implementation("com.google.guava:guava:$guavaVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")

        // Testing
        testImplementation("junit:junit:$junitVersion")
        androidTestImplementation("androidx.test.ext:junit:$androidxJunitVersion")
        androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    }

