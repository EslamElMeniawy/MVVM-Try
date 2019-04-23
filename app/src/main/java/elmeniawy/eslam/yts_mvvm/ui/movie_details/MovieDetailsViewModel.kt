package elmeniawy.eslam.yts_mvvm.ui.movie_details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent
import elmeniawy.eslam.yts_mvvm.root.DownloadClickCallback
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * MovieDetailsViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class MovieDetailsViewModel @Inject constructor(private val moshi: Moshi) : ViewModel() {
    val downloadAdapter: DownloadAdapter = DownloadAdapter(object : DownloadClickCallback {
        override fun onClick(torrent: Torrent) {
            externalLinkToOpen.value = torrent.url
        }
    })

    val errorVisibility: MutableLiveData<Int> = MutableLiveData()
    val dataVisibility: MutableLiveData<Int> = MutableLiveData()
    val backgroundUrl = MutableLiveData<String>()
    val movieTitle = MutableLiveData<String>()
    val year = MutableLiveData<String>()
    val genres = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val runtime = MutableLiveData<String>()
    val rating = MutableLiveData<String>()
    val synopsis = MutableLiveData<String>()

    val imdbClickListener = View.OnClickListener {
        if (::movie.isInitialized) {
            externalLinkToOpen.value = "https://www.imdb.com/title/${movie.imdbCode}"
        }
    }

    val externalLinkToOpen: MutableLiveData<String> = MutableLiveData()
    private lateinit var movie: Movie

    init {
        externalLinkToOpen.value = ""
        errorVisibility.value = View.GONE
        dataVisibility.value = View.VISIBLE
    }

    //region ViewModel methods
    fun setMovie(movieString: String) {
        if (movieString.isNotEmpty()) {
            val jsonAdapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
            movie = jsonAdapter.fromJson(movieString) ?: Movie()

            if (movie == Movie()) {
                dataVisibility.value = View.GONE
                errorVisibility.value = View.VISIBLE
            } else {
                errorVisibility.value = View.GONE
                dataVisibility.value = View.VISIBLE
                fillData()
            }
        } else {
            dataVisibility.value = View.GONE
            errorVisibility.value = View.VISIBLE
        }
    }

    fun clearExternalLink() {
        externalLinkToOpen.value = ""
    }
    //endregion

    //region Private methods
    private fun fillData() {
        backgroundUrl.value = movie.backgroundImage
        movieTitle.value = movie.title
        year.value = movie.year.toString()
        var genresString = ""

        movie.genres.forEach {
            genresString += "$it / "
        }

        if (genresString.endsWith(" / ")) {
            genresString = genresString.removeSuffix(" / ")
        }

        genres.value = genresString
        imageUrl.value = movie.mediumCoverImage

        val runtimeString = if (movie.runtime == 0.toLong()) {
            "--"
        } else {
            val hours = TimeUnit.MINUTES.toHours(movie.runtime)

            if (hours > 0) {
                val remainingMinutes = movie.runtime.minus(hours * 60)
                "$hours H\t\t$remainingMinutes M"
            } else {
                "${movie.runtime} M"
            }
        }

        runtime.value = runtimeString
        rating.value = String.format(Locale.getDefault(), "%.1f/10", movie.rating)
        downloadAdapter.updateTorrentsList(movie.torrents)
        synopsis.value = movie.synopsis
    }
    //endregion
}