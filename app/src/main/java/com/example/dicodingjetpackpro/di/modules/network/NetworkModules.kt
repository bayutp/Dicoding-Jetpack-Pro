package com.example.dicodingjetpackpro.di.modules.network

import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.api.ApiService
import com.example.dicodingjetpackpro.di.modules.BaseModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModules: BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(
            retrofitModule,
            webServiceModule
        )

    private val webServiceModule = module {
        single { get<Retrofit>().create(ApiService::class.java) }
    }

    private val retrofitModule = module {
        single { provideOkHttpClient() }
        single {
            provideRetrofit(
                get()
            )
        }
    }

    private fun provideOkHttpClient(): OkHttpClient{
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}