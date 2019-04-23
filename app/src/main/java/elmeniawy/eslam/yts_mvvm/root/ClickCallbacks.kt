package elmeniawy.eslam.yts_mvvm.root

import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent

/**
 * ClickCallbacks
 *
 * Created by Eslam El-Meniawy on 22-Apr-2019.
 * Roqay
 */
interface MovieClickCallback {
    fun onClick(movie: Movie)
}

interface DownloadClickCallback {
    fun onClick(torrent: Torrent)
}