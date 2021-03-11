package planday.review.app.models

sealed class NewsResponse {
    class Success(val status: String, val totalResults: Int, val articles: List<Article>) : NewsResponse()
    object Error: NewsResponse()
}