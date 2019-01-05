package com.gaelmarhic.di.data.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by Gaël Marhic on 05/01/2019.
 */

/**
 * Extension function used to enable logs on a OkHttpClient.Builder object.
 */
fun OkHttpClient.Builder.enableLogs() {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    addInterceptor(logging)
}

/**
 * Extension function used to make server requests retry a certain number of times when they fail.
 * @param timeOut The connection's timeout.
 * @param attempts The number of times that we want to retry in case of failure.
 */
fun OkHttpClient.Builder.setFailureHandler(timeOut: Long, attempts: Int) {

    connectTimeout(timeOut, SECONDS)

    addInterceptor { chain ->
        val request = chain.request()
        var response = chain.proceed(request)
        var tryCount = 0

        while (!response.isSuccessful && tryCount < attempts) {
            tryCount++
            response = chain.proceed(request)
        }
        response
    }
}
