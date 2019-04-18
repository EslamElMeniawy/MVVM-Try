package elmeniawy.eslam.yts_mvvm.root

import android.util.ArrayMap
import androidx.lifecycle.ViewModelProvider
import elmeniawy.eslam.yts_mvvm.di.ViewModelSubComponent
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import elmeniawy.eslam.yts_mvvm.ui.home.HomeViewModel
import elmeniawy.eslam.yts_mvvm.ui.home.MovieItemViewModel
import elmeniawy.eslam.yts_mvvm.ui.movie_details.DownloadItemViewModel
import elmeniawy.eslam.yts_mvvm.ui.movie_details.MovieDetailsViewModel
import elmeniawy.eslam.yts_mvvm.ui.splash.SplashViewModel
import java.util.concurrent.Callable

/**
 * ViewModelFactory
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class ViewModelFactory @Inject constructor(private val viewModelSubComponent: ViewModelSubComponent) :
    ViewModelProvider.Factory {
    private var creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        creators[SplashViewModel::class.java] = Callable { viewModelSubComponent.splashViewModel() }
        creators[HomeViewModel::class.java] = Callable { viewModelSubComponent.homeViewModel() }
        creators[MovieItemViewModel::class.java] = Callable { viewModelSubComponent.movieItemViewModel() }
        creators[MovieDetailsViewModel::class.java] = Callable { viewModelSubComponent.movieDetailsViewModel() }
        creators[DownloadItemViewModel::class.java] = Callable { viewModelSubComponent.downloadItemViewModel() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]

        if (creator == null) {
            for (entry in creators.entries) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    creator = entry.value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }

        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}