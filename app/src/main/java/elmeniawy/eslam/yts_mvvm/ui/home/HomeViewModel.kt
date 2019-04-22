package elmeniawy.eslam.yts_mvvm.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.model.api.ApiRepository
import elmeniawy.eslam.yts_mvvm.model.data_classes.MoviesResponse
import elmeniawy.eslam.yts_mvvm.model.database.DatabaseRepository
import elmeniawy.eslam.yts_mvvm.utils.isNetworkThrowable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * HomeViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    val moviesAdapter: MoviesAdapter = MoviesAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorVisibility: MutableLiveData<Int> = MutableLiveData()
    val dataVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }
    private lateinit var deferred: Deferred<Response<MoviesResponse>>
    private var pageToLoad: Long = 1

    init {
        loadMovies()
    }

    //region Private methods
    private fun loadMovies() {
        errorVisibility.value = View.GONE
        dataVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE

        viewModelScope.launch(Dispatchers.IO) {
            try {
                deferred = apiRepository.getMoviesAsync(pageToLoad)
                val response = deferred.await()

                handleResponse(response.body())
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    private fun handleResponse(moviesResponse: MoviesResponse?) {
        moviesResponse?.let {
            if (moviesResponse.status == "ok") {
                if (moviesResponse.data.movies.isNotEmpty()) {
                    errorMessage.value = null
                    moviesAdapter.updateMoviesList(moviesResponse.data.movies)
                    dataVisibility.value = View.VISIBLE
                } else {
                    errorMessage.value = R.string.no_movies_available
                    errorVisibility.value = View.VISIBLE
                }
            } else {
                errorMessage.value = R.string.error_get_movies
                errorVisibility.value = View.VISIBLE
            }
        }

        loadingVisibility.value = View.GONE
    }

    private fun handleException(exception: Exception) {
        Timber.e(exception)
        var messageId = R.string.error_get_movies

        if (isNetworkThrowable(exception)) {
            messageId = R.string.connection_error
        }

        errorMessage.value = messageId
        errorVisibility.value = View.VISIBLE
        loadingVisibility.value = View.GONE
    }
    //endregion
}