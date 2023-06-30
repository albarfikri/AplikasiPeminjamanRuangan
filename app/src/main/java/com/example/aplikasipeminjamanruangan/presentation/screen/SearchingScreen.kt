package com.example.aplikasipeminjamanruangan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModelMain
import com.example.aplikasipeminjamanruangan.presentation.components.home.TopAppBar
import com.example.aplikasipeminjamanruangan.presentation.screen.home.ListRoomBasedOnFloor
import com.example.aplikasipeminjamanruangan.presentation.states.RealtimeDBGetPeminjamanState
import com.example.aplikasipeminjamanruangan.presentation.states.RealtimeDBRoomsState
import com.example.aplikasipeminjamanruangan.presentation.utils.AnimateShimmer
import com.example.aplikasipeminjamanruangan.presentation.utils.CustomDialog
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.PeminjamanViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchingScreen(
    onNavBack: () -> Unit,
    appViewModel: AppViewModel,
    onHeadingToDetail: (RoomsModelMain) -> Unit,
    peminjamanViewModel: PeminjamanViewModel,
    modifier: Modifier = Modifier
) {
    val getPeminjaman = peminjamanViewModel.getPeminjaman.collectAsStateWithLifecycle().value
    val roomsState by appViewModel.roomsState.collectAsStateWithLifecycle()
    var onSearchClicked by rememberSaveable {
        mutableStateOf(false)
    }

    var tanggalValue by rememberSaveable { mutableStateOf("") }
    var jMulaiValue by rememberSaveable { mutableStateOf("") }
    var jSelesaiValue by rememberSaveable { mutableStateOf("") }


    Scaffold() {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxSize()
        ) {
            if (onSearchClicked) {
                CustomDialog(
                    onDismiss = { onSearchClicked = false },
                    onConfirm = { data ->
                        tanggalValue = data[0]
                        jMulaiValue = data[1]
                        jSelesaiValue = data[2]
                    }
                )
            }
            TopAppBar(
                onNavBack = onNavBack,
                modifier = modifier,
                bigText = "Ruangan",
                smallText = "Cari",
                onSearch = {
                    onSearchClicked = true
                },
                onSearchAvailable = true
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(top = 24.dp)
            ) {
                when {
                    roomsState.isLoading -> {
                        AnimateShimmer(modifier = modifier, items = 200, height = 1000)
                    }

                    roomsState.data.isNullOrEmpty() -> {

                    }

                    !roomsState.data.isNullOrEmpty() -> {
                        SpreadingData(
                            getPeminjaman = getPeminjaman,
                            roomsStates = roomsState,
                            onHeadingToDetail = onHeadingToDetail,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpreadingData(
    getPeminjaman: RealtimeDBGetPeminjamanState,
    roomsStates: RealtimeDBRoomsState,
    onHeadingToDetail: (RoomsModelMain) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), content = {
            getPeminjaman.data?.forEach {
                items(roomsStates.data!!) { data ->
                    if (data?.item?.nama_ruangan != it?.ruangan)
                        ListRoomBasedOnFloor(
                            item = data,
                            onHeadingToDetail = onHeadingToDetail,
                            modifier = modifier
                        )
                }
            }
        },
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start
    )
}

