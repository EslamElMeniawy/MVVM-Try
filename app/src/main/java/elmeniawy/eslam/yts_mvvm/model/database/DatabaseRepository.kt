package elmeniawy.eslam.yts_mvvm.model.database

import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import javax.inject.Inject

/**
 * DatabaseRepository
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class DatabaseRepository @Inject constructor(private val moviesDao: MoviesDao) : DatabaseRepo {
    override suspend fun insertMovies(movies: List<Movie>) = moviesDao.insertMovies(movies)

    override suspend fun getMovies(): List<Movie> = moviesDao.getMovies()

    override suspend fun deleteMovies() = moviesDao.deleteMovies()
}