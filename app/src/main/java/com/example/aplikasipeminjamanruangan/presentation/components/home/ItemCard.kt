package com.example.aplikasipeminjamanruangan.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel

@Composable
fun ItemCard(
    item: RoomsModel,
    onHeadingToDetail: (RoomsModel) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .height(270.dp)
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onHeadingToDetail(item) },
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
        elevation = 10.dp
    ) {
        Box {
            Column {
                AsyncImage(
                    modifier = Modifier.height(180.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.foto_ruangan)
                        .crossfade(true)
                        .build(),
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
//            OutlinedButton(onClick = { /*TODO*/ }, modifier = modifier.padding(start = 2.dp)) {
//                Text(text = "Detail")
//            }
        }
    }
}