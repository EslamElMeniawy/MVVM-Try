package elmeniawy.eslam.yts_mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import elmeniawy.eslam.yts_mvvm.ui.home.HomeFragment
import elmeniawy.eslam.yts_mvvm.ui.movie_details.MovieDetailsFragment
import elmeniawy.eslam.yts_mvvm.ui.splash.SplashFragment

/**
 * FragmentBuildersModule
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindMovieDetailsFragment(): MovieDetailsFragment
}