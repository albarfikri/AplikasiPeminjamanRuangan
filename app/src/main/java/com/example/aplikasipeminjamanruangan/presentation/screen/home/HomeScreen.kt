package com.example.aplikasipeminjamanruangan.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.aplikasipeminjamanruangan.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel

@Composable
fun HomeScreen(modifier: Modifier, appViewModel: AppViewModel) {
    Column(
        modifier = modifier.padding(20.dp)
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
        Spacer(modifier = modifier.size(2.dp))
        DataListRoomI(modifier = modifier)
        DataListRoomII(modifier = modifier)
        DataListRoomIII(modifier = modifier)
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
fun ItemCard(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(270.dp),
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
            backgroundColor = Color.White,
            border = BorderStroke(width = 3.dp, Color.Gray),
            elevation = 10.dp
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Image",
                    modifier = modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Ruangan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "Fasilitas",
                        color = Color.LightGray,
                        fontSize = 13.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    ItemCard(modifier = Modifier)
}
