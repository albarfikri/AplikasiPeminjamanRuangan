package com.example.aplikasipeminjamanruangan.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aplikasipeminjamanruangan.presentation.states.RealtimeDBRoomsState
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.presentation.components.home.ItemCard
import com.example.aplikasipeminjamanruangan.presentation.utils.AnimateShimmer
import com.example.aplikasipeminjamanruangan.presentation.utils.FIRST_FLOOR
import com.example.aplikasipeminjamanruangan.presentation.utils.SECOND_FLOOR
import com.example.aplikasipeminjamanruangan.presentation.utils.THIRD_FLOOR
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel


@Composable
fun HomeScreen(
    appViewModel: AppViewModel,
    onHeadingToDetail: (RoomsModel) -> Unit,
    modifier: Modifier
) {
    val roomsState by appViewModel.roomsState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(color = MaterialTheme.colors.onPrimary, text = buildAnnotatedString {
            append("Hai, ")
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            ) {
                append("Mahasiswa")
            }
        })
        CardFeature(modifier = modifier)
        Spacer(modifier = modifier.size(1.dp))
        when {
            roomsState.isLoading -> {
                FloorDescription(floorName = "I")
                AnimateShimmer(modifier = modifier)
                FloorDescription(floorName = "II")
                AnimateShimmer(modifier = modifier)
                FloorDescription(floorName = "III")
                AnimateShimmer(modifier = modifier)
            }

            roomsState.data.isNullOrEmpty() -> {

            }

            !roomsState.data.isNullOrEmpty() -> {
                FloorDescription(floorName = "I")
                SpreadingData(
                    roomsState = roomsState,
                    floor = FIRST_FLOOR,
                    onHeadingToDetail = onHeadingToDetail,
                    modifier = modifier
                )
                FloorDescription(floorName = "II")
                SpreadingData(
                    roomsState = roomsState,
                    floor = SECOND_FLOOR,
                    onHeadingToDetail = onHeadingToDetail,
                    modifier = modifier
                )
                FloorDescription(floorName = "III")
                SpreadingData(
                    roomsState = roomsState,
                    floor = THIRD_FLOOR,
                    onHeadingToDetail = onHeadingToDetail,
                    modifier = modifier
                )
            }
        }

    }
}

@Composable
fun SpreadingData(
    roomsState: RealtimeDBRoomsState,
    onHeadingToDetail: (RoomsModel) -> Unit,
    floor: String,
    modifier: Modifier
) {
    LazyRow(

    ) {
        items(roomsState.data!!) { data ->
            if (data?.lantai_ruangan.equals(floor)) {
                ListRoomBasedOnFloor(
                    item = data,
                    onHeadingToDetail = onHeadingToDetail,
                    modifier = modifier
                )
            }
        }
    }
}


@Composable
fun CardFeature(modifier: Modifier) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(modifier = modifier) {
        }
    }
}


@Composable
fun ListRoomBasedOnFloor(
    item: RoomsModel?,
    onHeadingToDetail: (RoomsModel) -> Unit,
    modifier: Modifier
) {
    if (item != null) {
        ItemCard(
            item = item,
            onHeadingToDetail = onHeadingToDetail,
            modifier = modifier
        )
    }
}

@Composable
fun FloorDescription(floorName: String) {
    Spacer(modifier = Modifier.size(3.dp))
    Text(
        text = "Lantai $floorName",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onPrimary
    )
}

