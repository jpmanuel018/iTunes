package com.questro.itunes.data.remote

import com.questro.itunes.data.remote.dto.MusicInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    @GET("search")
    suspend fun getMusicInfo(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("limit") limit: String
    ): Response<MusicInfoDto>
}