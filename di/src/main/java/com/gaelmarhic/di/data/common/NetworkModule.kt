package com.gaelmarhic.di.data.common

import com.gaelmarhic.data.features.bitcoin.constants.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class NetworkModule {

    companion object {

        private const val BLOCKCHAIN_BASE_URL = "BLOCKCHAIN_BASE_URL"
        private const val DEFAULT_CONNECT_TIMEOUT = 30L
        private const val CONNECTION_ATTEMPTS = 3
    }

    @Provides
    @Named(BLOCKCHAIN_BASE_URL)
    fun provideBlockchainBaseUrl() = Constants.BLOCKCHAIN_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        enableLogs()
        setFailureHandler(DEFAULT_CONNECT_TIMEOUT, CONNECTION_ATTEMPTS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BLOCKCHAIN_BASE_URL) baseUrl: String,
                        okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
}
