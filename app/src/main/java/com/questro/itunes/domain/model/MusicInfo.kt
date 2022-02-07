package com.questro.itunes.domain.model

import com.questro.itunes.data.remote.dto.ResultDto

data class MusicInfo(
    val results: List<ResultDto>
)