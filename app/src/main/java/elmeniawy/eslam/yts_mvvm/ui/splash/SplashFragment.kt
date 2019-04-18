package elmeniawy.eslam.yts_mvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.di.Injectable

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}