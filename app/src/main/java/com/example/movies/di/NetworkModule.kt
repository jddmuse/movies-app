package com.example.movies.di

import com.example.movies.data.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //proveer retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val API_KEY3 = "28dac3a02da5157bd654dd07bfde85eb"
        val API_KEY4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyOGRhYzNhMDJkYTUxNTdiZDY1NGRkMDdiZmRlODVlYiIsInN1YiI6IjYyYTUxM2U5Yjg3YWVjMDA3MTc2NTExMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JQ2P0aqhNGi_WGbYAoDeABk3pyIQ9SaA_gCvSTW2etg"
        val API_URL = "https://api.themoviedb.org/"

        val authInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", API_KEY3)
                //.addQueryParameter("language", "en-US")
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(newRequest)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /*val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()*/
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit):MovieApiClient
        = retrofit.create(MovieApiClient::class.java)


}