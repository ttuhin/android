package com.tuhin.mymovies.di

import com.tuhin.mymovies.constant.RemoteConstant
import com.tuhin.mymovies.data.remote.MovieApi
import com.tuhin.mymovies.data.repository.MovieRepositoryImp
import com.tuhin.mymovies.domain.repository.MovieRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieApi {
        return Retrofit.Builder()
            .baseUrl(RemoteConstant.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieApi) : MovieRepository {
        return MovieRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(RemoteConstant.TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(RemoteConstant.TIME_OUT, TimeUnit.SECONDS)
            .run {
                addInterceptor { chain-> getResponseOfApiKeyRequest(chain) }
                addInterceptor(provideLoggingInterceptor())
            }.build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun getResponseOfApiKeyRequest(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(RemoteConstant.API_KEY, RemoteConstant.API_KEY_VALUE).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

//    @Provides
//    @Singleton
//    fun provideAdapter(): MovieAdapter = MovieAdapter(@ActivityContext,ArrayList())
}