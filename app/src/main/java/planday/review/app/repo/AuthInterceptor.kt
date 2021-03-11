package planday.review.app.repo

import okhttp3.Interceptor
import okhttp3.Response
import planday.review.app.BuildConfig

class AuthInterceptor: Interceptor {

    companion object {
        private const val AUTH_TOKEN_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(AUTH_TOKEN_HEADER, BuildConfig.API_AUTH_TOKEN)
                .build()
        )
    }
}