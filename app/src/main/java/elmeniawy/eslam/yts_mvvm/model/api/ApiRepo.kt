package elmeniawy.eslam.yts_mvvm.model.api

import elmeniawy.eslam.yts_mvvm.model.data_classes.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * ApiRepo
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
interface ApiRepo {
    fun getMoviesAsync(page: Long): Deferred<Response<MoviesResponse>>
}