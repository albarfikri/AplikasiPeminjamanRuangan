package com.example.aplikasipeminjamanruangan.presentation.states

import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel

data class RealtimeDBRoomsState(
    val data: List<RoomsModel?>? = null,
    val isLoading: Boolean = false,
    val errMsg: String? = null
)

data class RealtimeDbUpdateRoomsState(
    val data: String? = null,
    val isLoading: Boolean = false,
    val errMsg: String? = null
)