package elmeniawy.eslam.yts_mvvm.root

import android.app.Activity
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import elmeniawy.eslam.yts_mvvm.BuildConfig
import elmeniawy.eslam.yts_mvvm.di.AppInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * App
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class App : MultiDexApplication(), HasActivityInjector {
    //region Variables
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    //endregion

    //region Lifecycle methods
    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        initLibraries()
    }
    //endregion

    //region Injection
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
    //endregion

    //region Libraries initialization
    /**
     * Initialize libraries used in the application.
     */
    private fun initLibraries() {
        initTimber()
    }

    /**
     * Initialize Timber.
     * For "DEBUG" plant a "DebugTree" for logging in Logcat.
     */
    @Suppress("ConstantConditionIf")
    private fun initTimber() {
        if (BuildConfig.enableDebugLogging) {
            Timber.plant(Timber.DebugTree())
        }
    }
    //endregion
}