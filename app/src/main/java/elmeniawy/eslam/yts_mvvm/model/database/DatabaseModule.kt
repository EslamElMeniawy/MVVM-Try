package elmeniawy.eslam.yts_mvvm.model.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import elmeniawy.eslam.yts_mvvm.utils.DATABASE_NAME
import javax.inject.Singleton

/**
 * DatabaseModule
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module
class DatabaseModule {
    /**
     * Provide instance of [AppDatabase].
     */
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) =
        Room
            .databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .build()

    /**
     * Provide instance of [MoviesDao].
     */
    @Provides
    @Singleton
    fun provideMoviesDao(appDatabase: AppDatabase) = appDatabase.moviesDao()

    /**
     * Provide instance of [DatabaseRepository].
     */
    @Provides
    @Singleton
    fun provideDatabaseRepository(moviesDao: MoviesDao): DatabaseRepository = DatabaseRepository(moviesDao)
}