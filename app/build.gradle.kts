plugins {
    id(GradlePlugins.android)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExt)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.navSafeArg)
}

android {
    compileSdkVersion(Android.compileSdk)
    buildToolsVersion(Android.buildTools)

    defaultConfig {
        applicationId = Android.applicationId

        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode
        versionName = Android.versionNam

        testInstrumentationRunner = AndroidJUnit.testInstrumentationRunner

        multiDexEnabled = true
    }

    buildTypes {
        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFile(BuildType.proguardDebug)
        }

        getByName(BuildType.release) {
            isMinifyEnabled = BuildType.minifyRelease
            proguardFile(BuildType.proguardRelease)
        }
    }

    flavorDimensions("version")
    productFlavors {
        create(ProductFlavor.develop) {
            applicationId = ProductFlavor.applicationIdDevelop
            versionCode = ProductFlavor.versionCodeDevelop
            versionName = ProductFlavor.versionNameDevelop

            buildConfigField(
                "String",
                ProductFlavor.baseUrlParam,
                ProductFlavor.baseUrlDevelop
            )
        }

        create(ProductFlavor.staging) {
            applicationId = ProductFlavor.applicationIdStaging
            versionCode = ProductFlavor.versionCodeStaging
            versionName = ProductFlavor.versionNameStaging

            buildConfigField(
                "String",
                ProductFlavor.baseUrlParam,
                ProductFlavor.baseUrlStaging
            )
        }

        create(ProductFlavor.production) {
            applicationId = ProductFlavor.applicationIdProduction
            versionCode = ProductFlavor.versionCodeProduction
            versionName = ProductFlavor.versionNameProduct

            buildConfigField(
                "String",
                ProductFlavor.baseUrlParam,
                ProductFlavor.baseUrlProduction
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    implementation(BuildPlugins.stdlib)

    // ConstraintLayout
    implementation(Libs.constraintLayout)

    // Appcompat
    implementation(Libs.appcompat)

    // Android Core
    implementation(Libs.coreKtx)

    // ViewModel + LiveData Lifecycle
    implementation(Libs.viewModel)
    implementation(Libs.liveData)
    implementation(Libs.lifecycleProcessor)

    // Multidex
    implementation(Libs.multidex)

    // Navigation
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)

    // RecyclerView
    implementation(Libs.recyclerView)

    // Room
    implementation(Libs.room)
    implementation(Libs.roomExt)
    kapt(Libs.roomProcessor)

    // ViewPager2
    implementation(Libs.viewPager2)

    // Koin
    implementation(Libs.koin)
    implementation(Libs.koinScope)
    implementation(Libs.koinViewModel)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // OkHttp
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLogging)
    testImplementation(Libs.okHttpMockServer)

    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideProcessor)

    // JUnit
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.jUnitExt)
    androidTestImplementation(Libs.espresso)

    // Mockito
    implementation(Libs.mockito)

    // Material Design
    implementation(Libs.material)

    // CardView
    implementation(Libs.cardView)

    // Preference
    implementation(Libs.preference)
}