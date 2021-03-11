package planday.review.app.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import planday.review.app.BuildConfig
import planday.review.app.repo.AuthInterceptor
import planday.review.app.repo.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val networkModule = module {

    single(createdAtStart = false) { get<Retrofit>().create(NewsApi::class.java) }

    single { retrofitHttpClient() }
    single { retrofitBuilder() }

    factory { AuthInterceptor() }
}

private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build()
}

private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(get<AuthInterceptor>())
        addInterceptor(HttpLoggingInterceptor { Timber.tag("HTTP").i(it)}
            .apply { level = HttpLoggingInterceptor.Level.BODY })
    }.build()
}