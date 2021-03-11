package planday.review.app.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import planday.review.app.R
import planday.review.app.databinding.ListRowMainBinding
import planday.review.app.models.Article

class MainAdapter : ListAdapter<Article, MainAdapter.MainViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            ListRowMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(val binding: ListRowMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

            Glide.with(binding.imageView)
                .load(article.urlToImage)
                .into(binding.imageView)

            binding.titleView.text = article.title
            binding.publishedView.text = article.publishedAt

            binding.root.setOnClickListener{
                binding.root.findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToDetailsFragment(article))
            }
        }
    }

}
