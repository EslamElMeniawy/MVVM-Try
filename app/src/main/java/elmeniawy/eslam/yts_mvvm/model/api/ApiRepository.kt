package elmeniawy.eslam.yts_mvvm.model.api

import elmeniawy.eslam.yts_mvvm.model.data_classes.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

/**
 * ApiRepository
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class ApiRepository @Inject constructor(private val apiService: ApiService) : ApiRepo {
    override fun getMoviesAsync(page: Long): Deferred<Response<MoviesResponse>> = apiService.getMoviesAsync(page)
}