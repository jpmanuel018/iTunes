package com.questro.itunes.data.remote.dto

import com.questro.itunes.domain.model.MusicInfo

data class MusicInfoDto(
    val results: List<ResultDto>
){
    fun toMusicInfo(): MusicInfo{
        return MusicInfo(results = results)
    }
}