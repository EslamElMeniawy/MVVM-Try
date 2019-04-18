package elmeniawy.eslam.yts_mvvm.model.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kizitonwose.time.days
import com.kizitonwose.time.minutes
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import elmeniawy.eslam.yts_mvvm.BuildConfig
import elmeniawy.eslam.yts_mvvm.utils.isNetworkAvailable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import java.util.*

/**
 * ApiModule
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module
class ApiModule {
    /**
     * Provide custom instance of [OkHttpClient].
     */
    @Provides
    @Singleton
    fun provideClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .cache(provideCache(context))
        .addNetworkInterceptor(provideNetworkInterceptor(context))
        .build()

    /**
     * Provide custom instance of [HttpLoggingInterceptor] to be attached to Retrofit for logging network calls.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()

        @Suppress("ConstantConditionIf")
        if (BuildConfig.enableDebugLogging) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return interceptor
    }

    /**
     * Provide [Cache] to be used in caching network calls.
     */
    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheDirectory = File(context.cacheDir, "YtsResponses")
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        return Cache(cacheDirectory, cacheSize)
    }

    /**
     * Provide [Interceptor] for adding online and offline cache to network requests.
     */
    @Provides
    @Singleton
    fun provideNetworkInterceptor(context: Context): Interceptor =
        Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (isNetworkAvailable(context)) {
                // Read from cache for 1 minute.
                val maxAge = 1.minutes.inSeconds.longValue

                return@Interceptor originalResponse.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, max-age=$maxAge"
                    )
                    .build()
            } else {
                // Tolerate 4-weeks stale.
                val maxStale = 28.days.inSeconds.longValue

                return@Interceptor originalResponse.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    )
                    .build()
            }
        }

    /**
     * Provide instance of [CoroutineCallAdapterFactory].
     */
    @Provides
    @Singleton
    fun provideCoroutineFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    /**
     * Provide instance of [MoshiConverterFactory].
     */
    @Provides
    @Singleton
    fun provideMoshiFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    /**
     * Provide instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(context: Context): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(provideClient(context))
        .addCallAdapterFactory(provideCoroutineFactory())
        .addConverterFactory(provideMoshiFactory())
        .build()
        .create(ApiService::class.java)

    /**
     * Provide instance of [ApiRepository].
     */
    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService): ApiRepository = ApiRepository(apiService)

    /**
     * Provide instance of [Moshi] with date adapter.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
}