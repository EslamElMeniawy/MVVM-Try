plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExtensions)
    kotlin(Plugins.kapt)
}

android {
    compileSdkVersion(Configs.compileSdkVersion)

    dataBinding {
        isEnabled = true
    }

    defaultConfig {
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.okhttp)
            proguardFiles(ProGuards.okio)
            proguardFiles(ProGuards.moshi)
            proguardFiles(ProGuards.moshiKotlin)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            buildConfigField("boolean", "enableDebugLogging", "true")
            buildConfigField("String", "PACKAGE_NAME", "\"elmeniawy.eslam.yts_mvvm.debug\"")
            buildConfigField("String", "BASE_URL", "\"https://yts.am/api/v2/\"")
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.okhttp)
            proguardFiles(ProGuards.okio)
            proguardFiles(ProGuards.moshi)
            proguardFiles(ProGuards.moshiKotlin)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            buildConfigField("boolean", "enableDebugLogging", "false")
            buildConfigField("String", "PACKAGE_NAME", "\"elmeniawy.eslam.yts_mvvm\"")
            buildConfigField("String", "BASE_URL", "\"https://yts.am/api/v2/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/main.kotlin_module")
        exclude("META-INF/atomicfu.kotlin_module")
    }
}

dependencies {
    // Libraries and jars.
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin.
    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.reflect)

    // Coroutines.
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Androidx.
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.AndroidX.legacySupportV4)
    implementation(Dependencies.AndroidX.multiDex)

    // Navigation.
    // The Navigation Architecture Component.
    // Simplifies the implementation of navigation between destinations in your app.
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation(Dependencies.Navigation.uiKtx)

    // Timber.
    // Logger with a small, extensible API
    // which provides utility on top of Android's normal Log class.
    implementation(Dependencies.timber)

    // Dagger.
    // Dependency injector.
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.androidSupport)
    kapt(Dependencies.Dagger.compiler)
    kapt(Dependencies.Dagger.androidProcessor)

    // Retrofit.
    // Type-safe HTTP client.
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converterMoshi)
    implementation(Dependencies.Retrofit.coroutinesAdapter)

    // Moshi Kotlin.
    // Required by reflection adapter to add adapters to moshi.
    implementation(Dependencies.Moshi.kotlin)

    // Moshi Adapters.
    // For adding custom adapters to be used with moshi.
    implementation(Dependencies.Moshi.adapters)

    // OkHttp Logging Interceptor.
    // For HTTP logging.
    implementation(Dependencies.Retrofit.loggingInterceptor)

    // Room.
    // Persistence library provides an abstraction layer over SQLite
    // to allow for more robust database access while harnessing the full power of SQLite.
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.coroutines)
    kapt(Dependencies.Room.compiler)

    // ButterKnife.
    // Field and method binding for Android views
    // which uses annotation processing to generate boilerplate code for you.
    implementation(Dependencies.ButterKnife.butterKnife)
    kapt(Dependencies.ButterKnife.compiler)

    // Picasso.
    // A powerful image downloading and caching library for Android.
    implementation(Dependencies.picasso)

    // Time.
    // Type-safe time calculations in Kotlin, powered by generics.
    implementation(Dependencies.time)

    // LiveData & ViewModel.
    implementation(Dependencies.Lifecycle.extensions)
    implementation(Dependencies.Lifecycle.viewModelKtx)
    kapt(Dependencies.Lifecycle.compiler)
}
