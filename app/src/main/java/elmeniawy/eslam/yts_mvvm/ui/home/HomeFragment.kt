package elmeniawy.eslam.yts_mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.di.Injectable
import elmeniawy.eslam.yts_mvvm.databinding.FragmentHomeBinding
import elmeniawy.eslam.yts_mvvm.utils.setProgressBarColor
import elmeniawy.eslam.yts_mvvm.utils.showErrorMessageDialog
import timber.log.Timber
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

    //region View Binding
    @Nullable
    @BindView(R.id.progress_loading)
    lateinit var loading: ProgressBar

    @Nullable
    @BindView(R.id.tv_error)
    lateinit var errorTV: AppCompatTextView

    @Nullable
    @BindView(R.id.swipe_refresh_movies)
    lateinit var moviesSwipeRefresh: SwipeRefreshLayout

    @Nullable
    @BindView(R.id.recycler_movies)
    lateinit var moviesRecycler: RecyclerView
    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ButterKnife.
        unBinder = ButterKnife.bind(this, view)

        // Set progress bar and refresh indicator colors.
        setProgressBarColor(context, loading)
        moviesSwipeRefresh.setColorSchemeResources(R.color.green, R.color.grey)

        // Set recycler view scroll detector.
        moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    Timber.v("Recycler view reached bottom.")
                    viewModel.loadMoreMovies()
                }
            }
        })
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
        binding.viewModel = viewModel
    }
    //endregion

    //region Private methods
    private fun observeViewModel(viewModel: HomeViewModel) {
        // Observe error message id for displaying error inside error text view.
        observeErrorMessageId()

        // Observe alert error message id for displaying error inside alert.
        observeAlertErrorMessageId()
    }

    private fun observeErrorMessageId() {
        viewModel.errorMessageId.observe(this, Observer { errorMessageId ->
            errorMessageId?.let {
                errorTV.text = getString(R.string.error_click_reload, getString(errorMessageId))
            }
        })
    }

    private fun observeAlertErrorMessageId() {
        viewModel.alertErrorMessageId.observe(this, Observer { alertErrorMessageId ->
            alertErrorMessageId?.let {
                showErrorMessageDialog(
                    activity,
                    getString(R.string.error_loading_offline, getString(alertErrorMessageId))
                )
            }
        })
    }
    //endregion
}
