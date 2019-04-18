package elmeniawy.eslam.yts_mvvm.model.api

import elmeniawy.eslam.yts_mvvm.model.data_classes.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
interface ApiService {
    @GET("list_movies.json?limit=20")
    fun getMoviesAsync(@Query("page") page: Long): Deferred<Response<MoviesResponse>>
}