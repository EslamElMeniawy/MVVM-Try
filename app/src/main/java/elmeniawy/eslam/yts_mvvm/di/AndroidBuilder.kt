package elmeniawy.eslam.yts_mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import elmeniawy.eslam.yts_mvvm.ui.MainActivity

/**
 * AndroidBuilder
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
@Module
abstract class AndroidBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity
}