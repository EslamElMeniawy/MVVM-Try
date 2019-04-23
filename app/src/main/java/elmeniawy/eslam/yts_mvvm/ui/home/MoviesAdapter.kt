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
class MoviesAdapter(private val movieClickCallback: MovieClickCallback) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var moviesList: MutableList<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent,
            false
        )

        binding.callback = movieClickCallback
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return if (::moviesList.isInitialized) moviesList.size else 0
    }

    fun setMoviesList(moviesList: List<Movie>) {
        this.moviesList = moviesList.toMutableList()
        notifyDataSetChanged()
    }

    fun addMovies(moviesList: List<Movie>) {
        val lastId = this.moviesList.lastIndex
        this.moviesList.addAll(moviesList)
        notifyItemRangeChanged(lastId, moviesList.size)
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieItemViewModel()

        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}