package elmeniawy.eslam.yts_mvvm.root

import dagger.Module
import elmeniawy.eslam.yts_mvvm.di.ViewModelSubComponent
import androidx.lifecycle.ViewModelProvider
import dagger.Provides
import javax.inject.Singleton

/**
 * ViewModelModule
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module(subcomponents = [ViewModelSubComponent::class])
class ViewModelModule {
    @Singleton
    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory =
        ViewModelFactory(viewModelSubComponent.build())
}