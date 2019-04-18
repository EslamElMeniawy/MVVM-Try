package elmeniawy.eslam.yts_mvvm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import elmeniawy.eslam.yts_mvvm.model.api.ApiModule
import elmeniawy.eslam.yts_mvvm.model.database.DatabaseModule
import elmeniawy.eslam.yts_mvvm.root.App
import elmeniawy.eslam.yts_mvvm.root.ViewModelModule
import javax.inject.Singleton

/**
 * AppComponent
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AndroidBuilder::class,
        ApiModule::class,
        DatabaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}