package elmeniawy.eslam.yts_mvvm.ui.movie_details

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * MovieDetailsViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class MovieDetailsViewModel @Inject constructor() : ViewModel() {
    val downloadAdapter: DownloadAdapter = DownloadAdapter()

    init {
        fillData()
    }

    private fun fillData() {}
}