package planday.review.app.repo

import planday.review.app.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") querry: String = "planday",
        @Query("pageSize") pageSize: Int = 100
    ): NewsResponse.Success
}