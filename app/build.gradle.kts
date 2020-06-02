plugins {
    id(com.example.buildsrc.GradlePlugins.androidApplication)
    id(com.example.buildsrc.GradlePlugins.kotlin)
    id(com.example.buildsrc.GradlePlugins.kotlinExt)
    id(com.example.buildsrc.GradlePlugins.kotlinKapt)
    id(com.example.buildsrc.GradlePlugins.navSafeArg)
}

android {
    compileSdkVersion(com.example.buildsrc.Android.compileSdk)
    buildToolsVersion(com.example.buildsrc.Android.buildTools)

    defaultConfig {
        applicationId = com.example.buildsrc.Android.applicationId

        minSdkVersion(com.example.buildsrc.Android.minSdk)
        targetSdkVersion(com.example.buildsrc.Android.targetSdk)

        versionCode = com.example.buildsrc.Android.versionCode
        versionName = com.example.buildsrc.Android.versionNam

        testInstrumentationRunner = com.example.buildsrc.AndroidJUnit.testInstrumentationRunner

        multiDexEnabled = true
    }

    buildTypes {
        getByName(com.example.buildsrc.BuildType.debug) {
            isMinifyEnabled = com.example.buildsrc.BuildType.minifyDebug
            proguardFile(com.example.buildsrc.BuildType.proguardDebug)
        }

        getByName(com.example.buildsrc.BuildType.release) {
            isMinifyEnabled = com.example.buildsrc.BuildType.minifyRelease
            proguardFile(com.example.buildsrc.BuildType.proguardRelease)
        }
    }

    flavorDimensions("version")
    productFlavors {
        create(com.example.buildsrc.ProductFlavor.develop) {
            applicationId = com.example.buildsrc.ProductFlavor.applicationIdDevelop
            versionCode = com.example.buildsrc.ProductFlavor.versionCodeDevelop
            versionName = com.example.buildsrc.ProductFlavor.versionNameDevelop

            buildConfigField(
                "String",
                com.example.buildsrc.ProductFlavor.baseUrlParam,
                com.example.buildsrc.ProductFlavor.baseUrlDevelop
            )
        }

        create(com.example.buildsrc.ProductFlavor.staging) {
            applicationId = com.example.buildsrc.ProductFlavor.applicationIdStaging
            versionCode = com.example.buildsrc.ProductFlavor.versionCodeStaging
            versionName = com.example.buildsrc.ProductFlavor.versionNameStaging

            buildConfigField(
                "String",
                com.example.buildsrc.ProductFlavor.baseUrlParam,
                com.example.buildsrc.ProductFlavor.baseUrlStaging
            )
        }

        create(com.example.buildsrc.ProductFlavor.production) {
            applicationId = com.example.buildsrc.ProductFlavor.applicationIdProduction
            versionCode = com.example.buildsrc.ProductFlavor.versionCodeProduction
            versionName = com.example.buildsrc.ProductFlavor.versionNameProduct

            buildConfigField(
                "String",
                com.example.buildsrc.ProductFlavor.baseUrlParam,
                com.example.buildsrc.ProductFlavor.baseUrlProduction
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(com.example.buildsrc.BuildPlugins.stdlib)

    // ConstraintLayout
    implementation(com.example.buildsrc.Libs.constraintLayout)

    // Appcompat
    implementation(com.example.buildsrc.Libs.appcompat)

    // Android Core
    implementation(com.example.buildsrc.Libs.coreKtx)

    // ViewModel + LiveData Lifecycle
    implementation(com.example.buildsrc.Libs.viewModel)
    implementation(com.example.buildsrc.Libs.liveData)
    implementation(com.example.buildsrc.Libs.lifecycleProcessor)

    // Multidex
    implementation(com.example.buildsrc.Libs.multidex)

    // Navigation
    implementation(com.example.buildsrc.Libs.navigationFragment)
    implementation(com.example.buildsrc.Libs.navigationUi)

    // RecyclerView
    implementation(com.example.buildsrc.Libs.recyclerView)

    // Room
    implementation(com.example.buildsrc.Libs.room)
    implementation(com.example.buildsrc.Libs.roomExt)
    implementation(com.example.buildsrc.Libs.roomProcessor)

    // ViewPager2
    implementation(com.example.buildsrc.Libs.viewPager2)

    // Koin
    implementation(com.example.buildsrc.Libs.koin)
    implementation(com.example.buildsrc.Libs.koinScope)
    implementation(com.example.buildsrc.Libs.koinViewModel)

    // Retrofit
    implementation(com.example.buildsrc.Libs.retrofit)
    implementation(com.example.buildsrc.Libs.retrofitGson)

    // OkHttp
    implementation(com.example.buildsrc.Libs.okHttp)
    implementation(com.example.buildsrc.Libs.okHttpLogging)
    testImplementation(com.example.buildsrc.Libs.okHttpMockServer)

    // Glide
    implementation(com.example.buildsrc.Libs.glide)
    kapt(com.example.buildsrc.Libs.glideProcessor)

    // JUnit
    testImplementation(com.example.buildsrc.Libs.jUnit)
    androidTestImplementation(com.example.buildsrc.Libs.jUnitExt)
    androidTestImplementation(com.example.buildsrc.Libs.espresso)

    // Mockito
    implementation(com.example.buildsrc.Libs.mockito)

    // Material Design
    implementation(com.example.buildsrc.Libs.material)

    // CardView
    implementation(com.example.buildsrc.Libs.cardView)
}
