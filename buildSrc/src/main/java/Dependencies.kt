@Suppress("unused")
object Dependencies {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.legacySupportV4}"
        const val multiDex = "androidx.multidex:multidex:${Versions.AndroidX.multiDex}"
    }

    object Navigation {
        const val fragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val uiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"

        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Moshi {
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
        const val coroutines = "androidx.room:room-coroutines:${Versions.AndroidX.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
    }

    object ButterKnife {
        const val butterKnife = "com.jakewharton:butterknife:${Versions.butterKnife}"
        const val compiler = "com.jakewharton:butterknife-compiler:${Versions.butterKnife}"
    }

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val time = "com.github.kizitonwose.time:time:${Versions.time}"

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycle}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidX.lifecycle}"
    }
}
