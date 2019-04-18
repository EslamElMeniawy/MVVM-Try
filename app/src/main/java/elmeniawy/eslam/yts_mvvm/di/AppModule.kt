package elmeniawy.eslam.yts_mvvm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * AppModule
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}