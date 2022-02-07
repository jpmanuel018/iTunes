package com.questro.itunes.domain.use_case.get_music_info

import com.questro.itunes.data.remote.dto.MusicInfoDto
import com.questro.itunes.data.repository.ITunesRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class GetMusicInfoUseCase @Inject constructor(
    private val repository: ITunesRepositoryImpl
) {

    suspend fun getInfo(term: String): Response<MusicInfoDto>{
        return repository.getMusicInfo(term)
    }

}