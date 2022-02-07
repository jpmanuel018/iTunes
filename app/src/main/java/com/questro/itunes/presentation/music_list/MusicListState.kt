package com.questro.itunes.presentation.music_list

import com.questro.itunes.data.remote.dto.ResultDto

open class MusicInfoUIState {
    object Idle : MusicInfoUIState()
    object Loading : MusicInfoUIState()
    data class Error(val message: String) : MusicInfoUIState()
    data class Success(val items: List<ResultDto>) : MusicInfoUIState()
}