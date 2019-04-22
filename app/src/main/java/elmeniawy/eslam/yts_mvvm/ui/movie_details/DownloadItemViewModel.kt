package elmeniawy.eslam.yts_mvvm.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent
import javax.inject.Inject

/**
 * DownloadItemViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class DownloadItemViewModel @Inject constructor() : ViewModel() {
    private val title = MutableLiveData<String>()

    fun bind(torrent: Torrent) {
        var downloadText = torrent.quality

        if (torrent.type.isNotEmpty()) {
            downloadText += " - ${torrent.type}"
        }

        if (torrent.size.isNotEmpty()) {
            downloadText += "\n${torrent.size}"
        }

        title.value = downloadText
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }
}