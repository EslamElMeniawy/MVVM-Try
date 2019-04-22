package elmeniawy.eslam.yts_mvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.databinding.FragmentSplashBinding
import elmeniawy.eslam.yts_mvvm.di.Injectable
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashFragment : Fragment(), Injectable {
    //region Variables
    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize view model.
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        observeViewModel(viewModel)
        binding.viewModel = viewModel
    }
    //endregion

    //region Private methods
    private fun observeViewModel(viewModel: SplashViewModel) {
        // Observe open home flag.
        viewModel.isOpenHome.observe(this, Observer { isOpenHome -> if (isOpenHome) openHome() })
    }

    private fun openHome() {
        if (activity != null) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()

            Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                .navigate(
                    R.id.action_splashFragment_to_homeFragment, null,
                    navOptions
                )
        }
    }
    //endregion
}
