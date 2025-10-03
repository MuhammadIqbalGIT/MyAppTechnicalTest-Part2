package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.JokeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("jokes/search")
    suspend fun searchChuckNorrisJokes(
        @Query("query") query: String
    ): Response<JokeSearchResponse>
}

