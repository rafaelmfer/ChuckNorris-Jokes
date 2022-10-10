package mobi.pulsus.challenge.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

private const val TIMEOUT_30 = 30L
private const val BASE_URL_CHUCK = "https://api.chucknorris.io/jokes/"

class HTTPClient {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
    }

    private val httpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_30, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_30, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val provideRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_CHUCK)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T : Any> create(clazz: KClass<T>): T = provideRetrofit.create(clazz.java)
}