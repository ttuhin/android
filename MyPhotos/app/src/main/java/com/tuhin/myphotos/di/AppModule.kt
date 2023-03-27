package com.tuhin.myphotos.di

import com.tuhin.myphotos.constant.RemoteConstant
import com.tuhin.myphotos.data.remote.JsonPlaceholderApi
import com.tuhin.myphotos.data.repository.JsonPlaceholderRepositoryImp
import com.tuhin.myphotos.domain.repository.JsonPlaceholderRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : JsonPlaceholderApi {
        return Retrofit.Builder()
            .baseUrl(RemoteConstant.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(JsonPlaceholderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api : JsonPlaceholderApi) : JsonPlaceholderRepository {
        return JsonPlaceholderRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(RemoteConstant.TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(RemoteConstant.TIME_OUT, TimeUnit.SECONDS)
            .run {
                addInterceptor(provideLoggingInterceptor())
            }.build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}