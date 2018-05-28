package br.com.oxxynet.chatoxxy.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit {

        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val timeout: Long = 5
            val client = OkHttpClient.Builder()

            client.networkInterceptors().add(logging)
            client.connectTimeout(timeout, TimeUnit.MINUTES)
            client.writeTimeout(timeout, TimeUnit.MINUTES)
            client.readTimeout(timeout, TimeUnit.MINUTES)

            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}
