package com.example.cinema_app.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log request details
        Log.d("LoggingInterceptor", "Sending request: ${request.method} ${request.url}")

        val response = chain.proceed(request)

        // Log response details
        Log.d("LoggingInterceptor", "Received response: ${response.code} ${response.message}")

        return response
    }
}