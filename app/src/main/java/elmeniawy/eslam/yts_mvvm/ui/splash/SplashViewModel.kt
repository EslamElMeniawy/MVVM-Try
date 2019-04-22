package elmeniawy.eslam.yts_mvvm.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kizitonwose.time.seconds
import elmeniawy.eslam.yts_mvvm.utils.SPLASH_SECONDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * SplashViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class SplashViewModel @Inject constructor() : ViewModel() {
    val isOpenHome: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isOpenHome.value = false

        viewModelScope.launch(Dispatchers.Default) {
            delay(SPLASH_SECONDS.seconds.inMilliseconds.longValue)

            viewModelScope.launch(Dispatchers.Main) {
                isOpenHome.value = true
            }
        }
    }
}