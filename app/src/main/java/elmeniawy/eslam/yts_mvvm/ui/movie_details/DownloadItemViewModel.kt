package elmeniawy.eslam.yts_mvvm.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent

/**
 * DownloadItemViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class DownloadItemViewModel : ViewModel() {
    private val title = MutableLiveData<String>()
    private lateinit var torrent: Torrent

    fun bind(torrent: Torrent) {
        this.torrent = torrent
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

    fun getTorrent(): Torrent {
        return torrent
    }
}