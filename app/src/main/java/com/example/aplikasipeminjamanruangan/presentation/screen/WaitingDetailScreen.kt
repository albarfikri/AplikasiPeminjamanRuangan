package com.example.aplikasipeminjamanruangan.presentation.screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aplikasipeminjamanruangan.R
import com.example.aplikasipeminjamanruangan.domain.model.PengajuanModel
import com.example.aplikasipeminjamanruangan.presentation.ui.generatePDF
import com.example.aplikasipeminjamanruangan.presentation.utils.PermissionDialog
import com.example.aplikasipeminjamanruangan.presentation.utils.ReadExternalStorageProvider
import com.example.aplikasipeminjamanruangan.presentation.utils.SwipingStates
import com.example.aplikasipeminjamanruangan.presentation.utils.WriteExternalStorageProvider
import com.example.aplikasipeminjamanruangan.presentation.utils.textColor
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.PermissionViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.SharedViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WaitingDetailScreen(
    sharedViewModel: SharedViewModel = viewModel(),
    onNavBack: () -> Unit = {},
) {

    val data = sharedViewModel.sharedPengajuanStated.collectAsStateWithLifecycle().value

    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
    val computedProgress by remember {
        derivedStateOf {
            if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction
            else 1f - swipingState.progress.fraction
        }
    }
    val startHeightNum = 300

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset, source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipingState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset, available: Offset, source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                override suspend fun onPostFling(
                    consumed: Velocity, available: Velocity
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState, thresholds = { _, _ ->
                        FractionalThreshold(0.05f)
                    }, orientation = Orientation.Vertical, anchors = mapOf(
                        0f to SwipingStates.COLLAPSED,
                        heightInPx to SwipingStates.EXPANDED,
                    )
                )
                .nestedScroll(nestedScrollConnection)
        ) {
            MotionLayout(
                modifier = Modifier.fillMaxSize(),
                start = ConstraintSet {
                    val header = createRefFor("header")
                    val body = createRefFor("body")
                    val content1 = createRefFor("content1")
                    val content2 = createRefFor("content2")
                    constrain(header) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.value(300.dp)
                    }
                    constrain(body) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.fillToConstraints
                        this.top.linkTo(header.bottom, 0.dp)
                        this.bottom.linkTo(parent.bottom, 0.dp)
                    }
                    constrain(content1) {
                        this.start.linkTo(header.start, 8.dp)
                        this.top.linkTo(header.top, 8.dp)
                    }
                    constrain(content2) {
                        this.start.linkTo(header.start)
                        this.end.linkTo(header.end)
                        this.bottom.linkTo(header.bottom, 24.dp)
                    }
                },
                end = ConstraintSet {
                    val header = createRefFor("header")
                    val body = createRefFor("body")
                    val content1 = createRefFor("content1")
                    val content2 = createRefFor("content2")
                    constrain(header) {
                        this.height = Dimension.value(60.dp)
                    }
                    constrain(body) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.fillToConstraints
                        this.top.linkTo(header.bottom, 0.dp)
                        this.bottom.linkTo(parent.bottom, 0.dp)
                    }
                    constrain(content1) {
                        this.start.linkTo(header.start, 24.dp)
                        this.top.linkTo(header.top, 8.dp)
                        this.bottom.linkTo(header.bottom, 8.dp)
                        this.height = Dimension.fillToConstraints
                    }
                    constrain(content2) {
                        this.start.linkTo(content1.end, 12.dp)
                        this.bottom.linkTo(header.bottom)
                        this.top.linkTo(header.top)
                    }
                },
                progress = computedProgress,
            ) {
                TopSection(
                    startHeightNum = startHeightNum,
                    swipingState = swipingState,
                    onNavBack = { onNavBack() },
                    data = data.data!!
                )
                BottomSection(data = data.data!!)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopSection(
    startHeightNum: Int,
    swipingState: SwipeableState<SwipingStates>,
    onNavBack: () -> Unit,
    data: PengajuanModel
) {
    Card(
        modifier = Modifier
            .height(startHeightNum.dp)
            .layoutId("header")
            .alpha(
                when (swipingState.progress.to) {
                    SwipingStates.COLLAPSED -> 0.0f
                    SwipingStates.EXPANDED -> 1.0f
                }
            ), shape = RoundedCornerShape(bottomEnd = 22.dp, bottomStart = 22.dp), elevation = 10.dp
    ) {
        AsyncImage(
            modifier = Modifier
                .height(startHeightNum.dp)
                .layoutId("header")
                .alpha(
                    when (swipingState.progress.to) {
                        SwipingStates.COLLAPSED -> 0.0f
                        SwipingStates.EXPANDED -> 1.0f
                    }
                ),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image),
            model = ImageRequest.Builder(LocalContext.current).data(data.fotoRuangan)
                .crossfade(true).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }


    IconButton(
        onClick = { onNavBack() }, modifier = Modifier
            .layoutId("content1")
            .clip(
                CircleShape
            )
            .background(MaterialTheme.colors.background)
            .border(
                width = 2.dp, shape = CircleShape, color = MaterialTheme.colors.secondary
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = textColor
        )
    }

    Text(
        text = "${data.ruangan}",
        color = textColor,

        modifier = Modifier
            .layoutId("content2")
            .clip(
                CutCornerShape(topStart = 10.dp, bottomEnd = 10.dp)
            )
            .background(
                when (swipingState.progress.to) {
                    SwipingStates.COLLAPSED -> Color.Transparent
                    SwipingStates.EXPANDED -> MaterialTheme.colors.secondary
                }
            )
            .padding(start = 28.dp, end = 28.dp, top = 8.dp, bottom = 8.dp),
        fontSize = 20.sp,
        style = MaterialTheme.typography.h2
    )
}

@Composable
fun BottomSection(data: PengajuanModel) {
    val viewModel = viewModel<PermissionViewModel>()
    val dialogQueue = viewModel.visiblePermissionDialogQueue
    val activity = LocalContext.current as Activity
    val permissionToRequest = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    val writePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.WRITE_EXTERNAL_STORAGE, isGranted = isGranted
            )
        })


    val readAndWritePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            perms.keys.forEach { permission ->
                viewModel.onPermissionResult(
                    permission = permission, isGranted = perms[permission] == true
                )
            }
        })

    dialogQueue.reversed().forEach { permission ->
        PermissionDialog(
            permissionTextProvider = when (permission) {
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> WriteExternalStorageProvider()
                Manifest.permission.READ_EXTERNAL_STORAGE -> ReadExternalStorageProvider()
                else -> {
                    return@forEach
                }
            }, isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                activity, permission
            ), onDismiss = viewModel::dismissDialog, onOkClick = {
                viewModel.dismissDialog()
                readAndWritePermissionResultLauncher.launch(
                    arrayOf(permission)
                )
            }, onGoToAppSettingsClick = activity::openAppSettings
        )
    }
    Box(
        modifier = Modifier
            .layoutId("body")
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
    ) {
        var downloadPdf by rememberSaveable {
            mutableStateOf(false)
        }
        val context = LocalContext.current

        if (downloadPdf) {
            generatePDF(context, data)
            downloadPdf = false
        }

        LaunchedEffect(Unit) {
            if (!data.pengajuanDiterima) Toast.makeText(
                context, "Tidak dapat download Bukti !\nPengajuan Belum diterima", Toast.LENGTH_LONG
            ).show()
            if(data.pengembalianDiterima)Toast.makeText(
                context, "Proses Peminjaman telah selesai", Toast.LENGTH_LONG
            ).show()
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Peminjam", style = MaterialTheme.typography.h2, color = textColor)
            Text(
                text = "${data.nama}", color = textColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Nim", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.nim}", color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Ruangan", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.ruangan}", color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Tanggal", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.tanggal}", color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Mulai - Selesai", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.jmulai} - ${data.jselesai}", color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Keperluan", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = if (data.keperluan!!.isEmpty()) "-" else data.keperluan, color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Unit", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.unit}", color = textColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Penanggung Jawab", style = MaterialTheme.typography.h2, color = textColor)
            Text(text = "${data.penanggungJawab}", color = textColor)
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = {
//                    readAndWritePermissionResultLauncher.launch(
//                        permissionToRequest
//                    )
//                    writePermissionResultLauncher.launch(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    )
                    //generatePDF3(context)
                    downloadPdf = true
                },
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = Color.LightGray,
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                enabled = data.pengajuanDiterima
            ) {
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download Bukti Pembayaran",
                        tint = if (data.pengajuanDiterima) textColor else Color.White
                    )
                    Text(
                        "Download Bukti Peminjaman",
                        color = if (data.pengajuanDiterima) textColor else Color.White,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }
    }
}


fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)
    ).also(
        ::startActivity
    )
}
