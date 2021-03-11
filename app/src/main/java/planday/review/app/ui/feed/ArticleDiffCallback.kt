package planday.review.app.ui.feed

import androidx.recyclerview.widget.DiffUtil
import planday.review.app.models.Article

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
}
