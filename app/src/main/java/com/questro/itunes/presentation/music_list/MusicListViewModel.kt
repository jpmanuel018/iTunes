package com.questro.itunes.presentation.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.questro.itunes.domain.use_case.get_music_info.GetMusicInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicListViewModel @Inject constructor(
    private val getMusicInfoUseCase: GetMusicInfoUseCase
    ): ViewModel() {

    private val _musicInfoUIState = MutableStateFlow<MusicInfoUIState>(MusicInfoUIState.Idle)
    val scanResultUIState: StateFlow<MusicInfoUIState> = _musicInfoUIState

    fun getInfo(term: String) = viewModelScope.launch{
        _musicInfoUIState.value = MusicInfoUIState.Loading
        val response = getMusicInfoUseCase.getInfo(term)
        if(response.isSuccessful && response.code() == 200){
            _musicInfoUIState.value = MusicInfoUIState.Success(response.body()!!.toMusicInfo().results)
        }else{
            _musicInfoUIState.value = MusicInfoUIState.Error(response.errorBody().toString())
        }
    }

}