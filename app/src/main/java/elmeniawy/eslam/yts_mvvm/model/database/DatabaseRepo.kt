package elmeniawy.eslam.yts_mvvm.model.database

import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie

/**
 * DatabaseRepo
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
interface DatabaseRepo {
    suspend fun insertMovies(movies: List<Movie>)

    suspend fun getMovies(): List<Movie>

    suspend fun deleteMovies()
}