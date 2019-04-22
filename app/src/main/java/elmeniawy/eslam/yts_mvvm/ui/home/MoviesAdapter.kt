package elmeniawy.eslam.yts_mvvm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.databinding.ItemMovieBinding
import elmeniawy.eslam.yts_mvvm.model.data_classes.Movie

/**
 * MoviesAdapter
 *
 * Created by Eslam El-Meniawy on 22-Apr-2019.
 * Roqay
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var moviesList: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return if (::moviesList.isInitialized) moviesList.size else 0
    }

    fun updateMoviesList(moviesList: List<Movie>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieItemViewModel()

        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}