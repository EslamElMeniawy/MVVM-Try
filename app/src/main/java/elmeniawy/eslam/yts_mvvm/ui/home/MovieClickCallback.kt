package elmeniawy.eslam.yts_mvvm.ui.home

import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie

/**
 * MovieClickCallback
 *
 * Created by Eslam El-Meniawy on 22-Apr-2019.
 * Roqay
 */
interface MovieClickCallback {
    fun onClick(movie: Movie)
}