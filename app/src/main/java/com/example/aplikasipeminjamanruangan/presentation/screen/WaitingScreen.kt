package com.example.aplikasipeminjamanruangan.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aplikasipeminjamanruangan.presentation.components.waiting.ItemCardWaiting
import com.example.aplikasipeminjamanruangan.presentation.states.RealtimeDBGetPengajuanState
import com.example.aplikasipeminjamanruangan.presentation.utils.AnimateWaitingListShimmer
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.PengajuanViewModel

@Composable
fun WaitingScreen(pengajuanViewModel: PengajuanViewModel) {
    val getPengajuan = pengajuanViewModel.getPengajuan.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxSize()
    ) {
        Text(color = MaterialTheme.colors.onPrimary, text = buildAnnotatedString {
            append("Waiting ")
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            ) {
                append("List")
            }
        })

        when {
            getPengajuan.isLoading -> {
                AnimateWaitingListShimmer(modifier = Modifier)
            }

            getPengajuan.data.isNullOrEmpty() -> {

            }

            !getPengajuan.data.isNullOrEmpty() -> {
                SpreadingData(getPengajuan = getPengajuan)
            }

            getPengajuan.errMsg!!.isNotEmpty() -> {
                Text(text = getPengajuan.errMsg)
            }
        }
    }
}

@Composable
fun SpreadingData(
    getPengajuan: RealtimeDBGetPengajuanState,
) {
    LazyColumn {
        items(getPengajuan.data!!) { data ->
            ItemCardWaiting(item = data!!)
        }
    }
    Log.d("Albar", getPengajuan.toString())
}
