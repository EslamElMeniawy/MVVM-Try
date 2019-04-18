package elmeniawy.eslam.yts_mvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import elmeniawy.eslam.yts_mvvm.utils.DATABASE_VERSION

/**
 * AppDatabase
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Database(entities = [Movie::class], version = DATABASE_VERSION)
@TypeConverters(DatabaseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}