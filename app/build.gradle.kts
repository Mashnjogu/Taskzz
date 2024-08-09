
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
    id("com.google.devtools.ksp")


//    id ("com.google.devtools.ksp") version("1.9.0-1.0.13")


}

android {
    namespace = "com.example.taskzz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.taskzz"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas".toString())
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
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17


    }

    kotlinOptions {
//        jvmTarget = "1.8"
        jvmTarget = "17"

//        jvmTarget = "17"

        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.time.ExperimentalTime",
//            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xcontext-receivers",
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }



//    applicationVariants.all {
//        addJavaSourceFoldersToModel(
//            File(buildDir, "generated/ksp/$name/kotlin")
//        )
//    }


}

kotlin{
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {

    val room_version = "2.6.1"



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("junit:junit:4.12")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    //testing
    testImplementation("junit:junit:4.13.2")
    testImplementation ("io.mockk:mockk:1.12.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:1.8.0-RC2")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.35.0-alpha")
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.35.0-alpha")
//    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")
//    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:1.6.4")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("app.cash.turbine:turbine:1.0.0")
    //hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
//    kapt "androidx.hilt:hilt-compiler:1.0.0"
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    //animation
    implementation ("androidx.compose.animation:animation:1.2.0")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //compose destination
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.9.55")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.55")
    ksp("androidx.room:room-compiler:2.6.1")
    //compose navigation
    implementation("androidx.navigation:navigation-compose:$2.7.7")
    //Pranav's datepicker
    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
    //room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    //compose testing
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
//    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

}

kapt {
    correctErrorTypes = true
}