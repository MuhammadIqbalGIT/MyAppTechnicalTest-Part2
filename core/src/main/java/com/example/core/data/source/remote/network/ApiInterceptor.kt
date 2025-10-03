package com.example.core.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor () : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        /* val token: String = runBlocking {
             session.getToken()
         }
 */
        val token= "txttoken"

        val lOriginalRequest = chain.request()
        val lRequest = lOriginalRequest.newBuilder().header("Authorization", "Bearer ")
            .method(lOriginalRequest.method, lOriginalRequest.body).build()

        return if (token.isEmpty()) {
            chain.proceed(lOriginalRequest)
        } else {
            chain.proceed(lRequest)
        }
    }
}