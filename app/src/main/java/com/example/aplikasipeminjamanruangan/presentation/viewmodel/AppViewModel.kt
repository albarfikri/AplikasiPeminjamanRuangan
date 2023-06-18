package com.example.aplikasipeminjamanruangan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.presentation.states.RealtimeDBRoomsState
import com.example.aplikasipeminjamanruangan.domain.usecase.IAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val AppUseCase: IAppUseCase
): ViewModel() {
    private val _roomsState = MutableStateFlow(RealtimeDBRoomsState())
    val roomsState: StateFlow<RealtimeDBRoomsState> = _roomsState.asStateFlow()
    init{
        getRooms()
    }
    private fun getRooms() = viewModelScope.launch {
        AppUseCase.getRooms().collect { result ->
            when(result){
                is Resource.Loading -> {
                    _roomsState.update { it.copy(data = null, isLoading = true, errMsg = null) }
                }
                is Resource.Success -> {
                    _roomsState.update { it.copy(data = result.data, isLoading = false, errMsg = null) }
                }
                is Resource.Error -> {
                    _roomsState.update { it.copy (data = null, isLoading = false, errMsg = result.exception.message)}
                }
            }
        }
    }
}