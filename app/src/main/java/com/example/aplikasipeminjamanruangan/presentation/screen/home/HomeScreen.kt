package com.example.aplikasipeminjamanruangan.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel

@Composable
fun HomeScreen(modifier: Modifier, appViewModel: AppViewModel) {
    val roomsState by appViewModel.roomsState.collectAsState()

    Column(
        modifier = modifier.padding(16.dp)
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
        DataListRoomI(modifier = modifier)
        when {
            !roomsState.data.isNullOrEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    items(roomsState.data!!) { data ->
                        ItemCard(
                            fotoRuangan = data?.foto_ruangan!!,
                            namaRuangan = data.nama_ruangan!!,
                            fasilitasRuangan = data.fasilitas_ruangan!!,
                            modifier = modifier
                        )
                    }
                }
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
fun DataListRoomI(modifier: Modifier) {
    FloorDescription(floorName = "I")
}

@Composable
fun DataListRoomII(modifier: Modifier) {
    FloorDescription(floorName = "II")
}

@Composable
fun DataListRoomIII(modifier: Modifier) {
    FloorDescription(floorName = "III")
}


@Composable
fun FloorDescription(floorName: String) {
    Text(
        text = "Lantai $floorName",
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun ItemCard(
    fotoRuangan: String,
    namaRuangan: String,
    fasilitasRuangan: String,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
        elevation = 10.dp
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.height(180.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(fotoRuangan)
                    .crossfade(true)
                    .build(),
                contentDescription = namaRuangan,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)){
                Text(
                    color = Color.Black,
                    text = namaRuangan,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                )
                Text(
                    text = fasilitasRuangan,
                    color = Color.LightGray,
                    fontSize = 13.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    //ItemCard(namaRuangan = "test", fasilitasRuangan = "Tets" , modifier = Modifier)
}
