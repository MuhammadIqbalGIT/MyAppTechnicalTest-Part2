package com.example.core.data.source.remote.datasource

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.JokeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChuckNorrisDataSource  @Inject constructor(private val api: ApiService) {
    fun searchChuckNorrisJokes(query: String): Flow<ApiResponse<List<JokeItem>>> =
        flow {
            try {
                val response = api.searchChuckNorrisJokes(query)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.result.isNotEmpty()) {
                        emit(ApiResponse.Success(body.result))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Error("Terjadi Kesalahan"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan: ${e.message}"))
            }
        }.flowOn(Dispatchers.IO)
}