package elmeniawy.eslam.yts_mvvm.ui.movie_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.databinding.FragmentMovieDetailsBinding
import elmeniawy.eslam.yts_mvvm.di.Injectable
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieDetailsFragment : Fragment(), Injectable {
    //region Variables
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize view model.
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
        observeViewModel()
        binding.viewModel = viewModel

        // Get movie data.
        var movieString = ""

        arguments?.let {
            movieString = MovieDetailsFragmentArgs.fromBundle(it).movieString
        }

        viewModel.setMovie(movieString)
    }
    //endregion

    //region Private methods
    private fun observeViewModel() {
        // Observe external link to open.
        observeExternalLinkToOpen()
    }

    private fun observeExternalLinkToOpen() {
        viewModel.externalLinkToOpen.observe(this, Observer { externalLinkToOpen ->
            if (externalLinkToOpen.isNotEmpty()) {
                openExternalLink(externalLinkToOpen)
                viewModel.clearExternalLink()
            }
        })
    }

    private fun openExternalLink(externalLink: String) {
        context?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(externalLink))

            if (browserIntent.resolveActivity(it.packageManager) != null) {
                it.startActivity(browserIntent)
            }
        }
    }
    //endregion
}
