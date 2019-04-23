package elmeniawy.eslam.yts_mvvm.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import elmeniawy.eslam.yts_mvvm.R
import elmeniawy.eslam.yts_mvvm.databinding.ItemDownloadBinding
import elmeniawy.eslam.yts_mvvm.model.data_classes.Torrent
import elmeniawy.eslam.yts_mvvm.root.DownloadClickCallback

/**
 * TorrentsAdapter
 *
 * Created by Eslam El-Meniawy on 22-Apr-2019.
 * Roqay
 */
class DownloadAdapter(private val downloadClickCallback: DownloadClickCallback) :
    RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {
    private lateinit var torrentsList: List<Torrent>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDownloadBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_download, parent,
            false
        )

        binding.callback = downloadClickCallback
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(torrentsList[position])
    }

    override fun getItemCount(): Int {
        return if (::torrentsList.isInitialized) torrentsList.size else 0
    }

    fun updateTorrentsList(torrentsList: List<Torrent>) {
        this.torrentsList = torrentsList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDownloadBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = DownloadItemViewModel()

        fun bind(torrent: Torrent) {
            viewModel.bind(torrent)
            binding.viewModel = viewModel
        }
    }
}