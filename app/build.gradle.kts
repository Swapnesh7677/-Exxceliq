plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.swapnesh.exxceliq"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.swapnesh.exxceliq"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation ("androidx.activity:activity-ktx:1.8.2")
    // navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava:2.1.0")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")


    // recycler
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // room
    implementation("androidx.room:room-runtime:2.2.5")

    kapt("androidx.room:room-compiler:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    //data store preferences
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Paging
    implementation("androidx.paging:paging-runtime:2.1.0")

    /// splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation ("com.github.bumptech.glide:glide:4.9.0")
    kapt ("com.github.bumptech.glide:compiler:4.9.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation  ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    // Testing-only dependencies
    testImplementation ("junit:junit:4.12")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.0.0")
    testImplementation("com.squareup.okhttp3:okhttp:4.0.0")
    testImplementation ("org.mockito:mockito-core:2.25.0")
    testImplementation ("androidx.arch.core:core-testing:2.0.0")

    androidTestImplementation ("android.arch.lifecycle:extensions:1.1.1")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("androidx.test:core:1.1.0")
    androidTestImplementation ("androidx.test:runner:1.1.1")
    androidTestImplementation ("androidx.test:rules:1.1.1")

    androidTestImplementation ("androidx.appcompat:appcompat:1.6.1")
    androidTestImplementation ("androidx.recyclerview:recyclerview:1.1.0-alpha05")
    androidTestImplementation ("com.google.android.material:material:1.0.0")

    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.1.1")
    androidTestImplementation ("androidx.test.ext:junit:1.1.0")
    androidTestImplementation ("androidx.test.uiautomator:uiautomator:2.2.0")
    androidTestImplementation ("androidx.work:work-testing:2.1.0")

    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("org.mockito:mockito-core:2.25.0")
    androidTestImplementation ("org.mockito:mockito-android:2.25.0")

    androidTestImplementation ("androidx.test.espresso:espresso-core:3.1.1") {
        exclude("com.androidx.support")
    }




}
kapt {
    correctErrorTypes = true
}