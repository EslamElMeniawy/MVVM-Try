package elmeniawy.eslam.yts_mvvm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import elmeniawy.eslam.yts_mvvm.utils.COLUMN_ID
import elmeniawy.eslam.yts_mvvm.utils.TABLE_MOVIES

/**
 * MoviesDao
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM $TABLE_MOVIES ORDER BY $COLUMN_ID DESC")
    suspend fun getMovies(): List<Movie>

    @Query("DELETE FROM $TABLE_MOVIES")
    suspend fun deleteMovies()
}