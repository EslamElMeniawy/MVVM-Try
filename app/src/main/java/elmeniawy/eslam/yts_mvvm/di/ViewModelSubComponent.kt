package elmeniawy.eslam.yts_mvvm.di

import dagger.Subcomponent
import elmeniawy.eslam.yts_mvvm.ui.home.HomeViewModel
import elmeniawy.eslam.yts_mvvm.ui.home.MovieItemViewModel
import elmeniawy.eslam.yts_mvvm.ui.movie_details.DownloadItemViewModel
import elmeniawy.eslam.yts_mvvm.ui.movie_details.MovieDetailsViewModel
import elmeniawy.eslam.yts_mvvm.ui.splash.SplashViewModel

/**
 * ViewModelSubComponent
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun splashViewModel(): SplashViewModel

    fun homeViewModel(): HomeViewModel

    fun movieItemViewModel(): MovieItemViewModel

    fun movieDetailsViewModel(): MovieDetailsViewModel

    fun downloadItemViewModel(): DownloadItemViewModel
}