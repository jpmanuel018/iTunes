package com.questro.itunes.data.repository

import com.questro.itunes.common.Constants
import com.questro.itunes.data.remote.ITunesApi
import com.questro.itunes.data.remote.dto.MusicInfoDto
import com.questro.itunes.domain.repository.ITunesRepository
import retrofit2.Response
import javax.inject.Inject

class ITunesRepositoryImpl @Inject constructor(
    private val api: ITunesApi
): ITunesRepository {

    override suspend fun getMusicInfo(term: String): Response<MusicInfoDto> {
        return api.getMusicInfo(term,Constants.COUNTRY,Constants.MEDIA,Constants.SEARCH_LIMIT)
    }

}