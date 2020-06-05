package com.aumarbello.carowners.di

import com.aumarbello.carowners.BuildConfig
import com.aumarbello.carowners.service.CarOwnersService
import com.aumarbello.carowners.service.FilterService
import com.aumarbello.carowners.utils.AppCSVParser
import com.aumarbello.carowners.utils.CSVParser
import com.aumarbello.carowners.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(loggingInterceptor)
        }

        builder.writeTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesFilterService(retrofit: Retrofit.Builder): FilterService {
        return retrofit.baseUrl(Constants.BASE_URL)
            .build()
            .create(FilterService::class.java)
    }

    @Provides
    @Singleton
    fun providesCarOwnerService(retrofit: Retrofit.Builder): CarOwnersService {
        return retrofit.baseUrl(Constants.DRIVE_BASE_URL)
            .build()
            .create(CarOwnersService::class.java)
    }

    @Provides
    @Singleton
    fun providesCSVParser(): CSVParser {
        return AppCSVParser()
    }
}