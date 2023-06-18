package com.example.aplikasipeminjamanruangan.presentation.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aplikasipeminjamanruangan.R
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel

@Composable
fun ItemCard(
    item: RoomsModel,
    onHeadingToDetail: (RoomsModel) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .height(260.dp)
            .width(190.dp)
            .padding(4.dp)
            .clickable { onHeadingToDetail(item) },
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
        elevation = 14.dp
    ) {
        Box {
            Column {
                AsyncImage(
                    modifier = Modifier.height(180.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.foto_ruangan)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    error = painterResource(id = R.drawable.ic_broken_image),
                    contentDescription = item.nama_ruangan,
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        color = Color.Black,
                        text = item.nama_ruangan!!,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    )
                    Text(
                        text = item.fasilitas_ruangan!!,
                        color = Color.LightGray,
                        fontSize = 13.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}