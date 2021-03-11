package planday.review.app.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(val source: ArticleSource, val author: String, val title: String, val description: String, val url: String,
                   val urlToImage: String, val publishedAt: String, val content: String) : Parcelable