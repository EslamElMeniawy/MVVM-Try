package elmeniawy.eslam.yts_mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.Unbinder
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.di.Injectable
import elmeniawy.eslam.yts_mvvm.databinding.FragmentHomeBinding
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment(), Injectable {
    //region Variables
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var unBinder: Unbinder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

//        binding.postList.layoutManager = LinearLayoutManager(
//            this, LinearLayoutManager.VERTICAL,
//            false
//        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ButterKnife.
        unBinder = ButterKnife.bind(this, view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Unbind view on view destroy.
        unBinder.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize view model.
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        observeViewModel(viewModel)
    }
    //endregion

    //region View methods
    private fun observeViewModel(viewModel: HomeViewModel) {}
    //endregion
}
