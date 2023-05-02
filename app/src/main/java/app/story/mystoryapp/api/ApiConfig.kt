package app.story.mystoryapp.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import app.story.mystoryapp.BuildConfig.API_BASE_URL
import okhttp3.logging.HttpLoggingInterceptor


class ApiConfig {
    companion object {

        var API_BASE_URL_MOCK: String? = null

        fun getApiService(): ApiServices {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL_MOCK ?: API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }
}