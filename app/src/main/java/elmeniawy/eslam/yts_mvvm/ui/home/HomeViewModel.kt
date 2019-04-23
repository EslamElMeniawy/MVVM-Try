package elmeniawy.eslam.yts_mvvm.ui.home

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.model.api.ApiRepository
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import elmeniawy.eslam.yts_mvvm.model.data_classes.MoviesResponse
import elmeniawy.eslam.yts_mvvm.model.database.DatabaseRepository
import elmeniawy.eslam.yts_mvvm.root.MovieClickCallback
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
    private val databaseRepository: DatabaseRepository,
    private val moshi: Moshi
) : ViewModel() {
    val moviesAdapter: MoviesAdapter = MoviesAdapter(object : MovieClickCallback {
        override fun onClick(movie: Movie) {
            val jsonAdapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
            Timber.d("MovieToOpen: ${jsonAdapter.toJson(movie)}")
            movieToOpen.value = jsonAdapter.toJson(movie)
        }
    })

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorVisibility: MutableLiveData<Int> = MutableLiveData()
    val dataVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    val alertErrorMessageId: MutableLiveData<Int> = MutableLiveData()
    val isRefreshIndicatorVisible: ObservableBoolean = ObservableBoolean()
    val errorClickListener = View.OnClickListener { loadMovies() }
    val movieToOpen: MutableLiveData<String> = MutableLiveData()
    private lateinit var deferred: Deferred<Response<MoviesResponse>>
    private var page: Long = 1
    private var isLoading: Boolean = true
    private var moviesCount: Long = 0

    init {
        movieToOpen.value = ""
        loadMovies()
    }

    //region ViewModel methods
    fun onRefresh() {
        page = 1
        isRefreshIndicatorVisible.set(true)
        getMovies(page)
    }

    fun loadMoreMovies() {
        if (!isLoading && (moviesCount > (page * 20))) {
            isRefreshIndicatorVisible.set(true)
            getMovies(page + 1)
        }
    }

    fun clearMovie() {
        movieToOpen.value = ""
    }
    //endregion

    //region Private methods
    private fun loadMovies() {
        page = 1
        errorVisibility.value = View.GONE
        dataVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        getMovies(page)
    }

    private fun getMovies(page: Long) {
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                deferred = apiRepository.getMoviesAsync(page)
                val response = deferred.await()

                viewModelScope.launch(Dispatchers.Main) {
                    handleResponse(response.body())
                }
            } catch (exception: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    handleException(exception)
                }
            }
        }
    }

    private fun handleResponse(moviesResponse: MoviesResponse?) {
        moviesResponse?.let {
            val jsonAdapter: JsonAdapter<MoviesResponse> = moshi.adapter(MoviesResponse::class.java)
            Timber.d("MoviesResponse: ${jsonAdapter.toJson(moviesResponse)}")

            if (moviesResponse.status == "ok") {
                if (moviesResponse.data.movies.isNotEmpty()) {
                    errorMessageId.value = null
                    dataVisibility.value = View.VISIBLE

                    // Set page returned from API.
                    setPage(moviesResponse.data.pageNumber)

                    // Set movies count.
                    setMoviesCount(moviesResponse.data.movieCount)

                    // Handle the returned movies list.
                    handleMovies(moviesResponse.data.movies)
                } else {
                    handleErrorDisplay(R.string.no_movies_available)
                }
            } else {
                handleErrorDisplay(R.string.error_get_movies)
            }
        }

        loadingVisibility.value = View.GONE
        isRefreshIndicatorVisible.set(false)
        isLoading = false
    }

    private fun handleException(exception: Exception) {
        Timber.e(exception)
        var messageId = R.string.error_get_movies

        if (isNetworkThrowable(exception)) {
            messageId = R.string.connection_error
        }

        handleErrorDisplay(messageId)
        loadingVisibility.value = View.GONE
        isRefreshIndicatorVisible.set(false)
        isLoading = false
    }

    private fun setPage(page: Long) {
        this.page = if (page == Long.MIN_VALUE) {
            1
        } else {
            page
        }
    }

    private fun setMoviesCount(moviesCount: Long) {
        this.moviesCount = if (moviesCount == Long.MIN_VALUE) {
            0
        } else {
            moviesCount
        }
    }

    private fun handleMovies(movies: List<Movie>) {
        when (page) {
            1.toLong() -> {
                // Display movies to user.
                moviesAdapter.setMoviesList(movies)

                // Save movies.
                saveMovies(movies)
            }

            else -> {
                // Add movies to current available movies list.
                moviesAdapter.addMovies(movies)
            }
        }
    }

    private fun saveMovies(movies: List<Movie>) {
        // Delete old movies from database if existing and save new movies for offline use.
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteMovies()
            databaseRepository.insertMovies(movies)
        }
    }

    private fun handleErrorDisplay(errorId: Int) {
        when (page) {
            1.toLong() -> loadOffline(errorId)
            else -> alertErrorMessageId.value = errorId
        }
    }

    private fun loadOffline(errorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val offlineMovies = databaseRepository.getMovies()
            val listType = Types.newParameterizedType(List::class.java, Movie::class.java)
            val jsonAdapter: JsonAdapter<List<Movie>> = moshi.adapter(listType)
            Timber.d("OfflineMovies: ${jsonAdapter.toJson(offlineMovies)}")
            handleOfflineMovies(offlineMovies, errorId)
        }
    }

    private fun handleOfflineMovies(offlineMovies: List<Movie>, errorId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            if (offlineMovies.isNotEmpty()) {
                moviesAdapter.setMoviesList(offlineMovies)
                alertErrorMessageId.value = errorId
                errorVisibility.value = View.GONE
                dataVisibility.value = View.VISIBLE
            } else {
                errorMessageId.value = errorId
                errorVisibility.value = View.VISIBLE
            }
        }
    }
    //endregion
}