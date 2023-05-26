package com.example.aplikasipeminjamanruangan.domain

import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel

data class RealtimeDBRoomsState(
    val data: List<RoomsModel?>? = null,
    val isLoading: Boolean =false,
    val errMsg: String? = null
)