package elmeniawy.eslam.yts_mvvm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import java.util.*
import javax.inject.Inject

/**
 * MovieItemViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class MovieItemViewModel @Inject constructor() : ViewModel() {
    private val movieTitle = MutableLiveData<String>()
    private val year = MutableLiveData<String>()
    private val rating = MutableLiveData<String>()
    private val genres = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()

    fun bind(movie: Movie) {
        movieTitle.value = movie.title
        year.value = movie.year.toString()
        rating.value = String.format(Locale.getDefault(), "%.1f/10", movie.rating)
        var genresText = ""

        when {
            movie.genres.size > 2 -> genresText = "${movie.genres[0]}\t\t${movie.genres[1]}"

            else -> {
                movie.genres.forEach {
                    genresText += "$it\t\t"
                }
            }
        }

        genres.value = genresText
        imageUrl.value = movie.mediumCoverImage
    }

    fun getMovieTitle(): MutableLiveData<String> {
        return movieTitle
    }

    fun getYear(): MutableLiveData<String> {
        return year
    }

    fun getRating(): MutableLiveData<String> {
        return rating
    }

    fun getGenres(): MutableLiveData<String> {
        return genres
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }
}