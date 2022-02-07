package com.questro.itunes.domain.repository

import com.questro.itunes.data.remote.dto.MusicInfoDto
import retrofit2.Response

interface ITunesRepository {

    suspend fun getMusicInfo(term: String): Response<MusicInfoDto>
}