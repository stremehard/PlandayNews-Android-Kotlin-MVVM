package planday.review.app.ui.feed

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import planday.review.app.models.NewsResponse
import planday.review.app.repo.NewsApi
import timber.log.Timber

class FeedViewModel(private val repo: NewsApi): ViewModel() {

    private val _data: MutableLiveData<NewsResponse> = MutableLiveData()

    val data: LiveData<NewsResponse> get() = _data

    private var searchQuerry: String = "planday"

    init {
        refreshData()
    }

    fun onRefresh() {

    }

    fun onSearch(querry: String) {
        searchQuerry = querry
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            val response = try {
                repo.getNews(searchQuerry)
            } catch (e: Exception) {
                Timber.e(e)
                NewsResponse.Error
            }

            _data.value = response
        }
    }
}
