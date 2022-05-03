package com.issart.talkingpets.ui.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Purple
import com.issart.talkingpets.ui.theme.TextTitleColor
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import com.issart.talkingpets.ui.utils.StringCallback
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Gallery(uri: MutableLiveData<String?>, updatePhoto: StringCallback) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }//delete
    val photoUri = uri.observeAsState()

    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        updatePhoto(uri.toString())

        if (uri != null) {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        if (it) {
            bitmap = BitmapFactory.decodeFile(photoUri.value)
            updatePhoto(photoUri.value)
        } else {
            updatePhoto(null)
            Toast.makeText(context, "Camera image didn't save.", Toast.LENGTH_SHORT).show()
        }
    }

    when (bitmap) {
        null -> Column(modifier = Modifier.padding(bottom = 70.dp)) {
            GalleryTitleText()
            GalleryButtonsRow(galleryLauncher, cameraLauncher, updatePhoto)
            AnimalGridLayout()
        }
        else -> {
//            savePhoto, openNewScreen
            PhotoFromGallery(bitmap!!)
        }
    }

}

@Composable
fun PhotoFromGallery(bitmap: Bitmap) {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        Card(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.LightGray
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "animal photo",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun GalleryTitleText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 36.dp, end = 36.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        TitleScreen(
            title = stringResource(id = R.string.create_first_animation),
        )
    }
}

@Composable
fun GalleryButtonsRow(
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    updatePhoto: StringCallback
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        GalleryButton(
            color = Blue,
            imageId = R.drawable.ic_gallery,
            description = "open gallery",
            launcher = launcher
        )

        CameraButton(
            color = Purple,
            imageId = R.drawable.ic_camera,
            description = "open camera",
            cameraLauncher = cameraLauncher,
            updatePhoto
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun AnimalGridLayout() {
    val data = getAnimalIdList()
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = Modifier,
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 32.dp, horizontal = 8.dp)
    ) {
        items(data) { animalPhotoId ->
            Card(
                modifier = Modifier.padding(4.dp),
                backgroundColor = Color.LightGray,
                onClick = {
                    showToast(context, "animal photo")
                }
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = animalPhotoId),
                    contentDescription = "animal photo",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun GalleryButton(
    color: Color,
    imageId: Int,
    description: String,
    launcher: ManagedActivityResultLauncher<String, Uri?>? = null
) {
    val configuration = LocalConfiguration.current
    val widthBox = configuration.screenWidthDp / 2
    val width = configuration.screenWidthDp * 0.45
    Box(
        modifier = Modifier.width(width = widthBox.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.width(width = width.dp),
            onClick = { launcher?.launch("image/*") },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(25),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
        ) {
            Image(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                painter = painterResource(id = imageId),
                contentDescription = description
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraButton(
    color: Color,
    imageId: Int,
    description: String,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>? = null,
    updatePhoto: StringCallback
) {
    val configuration = LocalConfiguration.current
    val widthBox = configuration.screenWidthDp / 2
    val width = configuration.screenWidthDp * 0.45

    val context = LocalContext.current

    val photoFile: File? = try {
        createImageFile(context)
    } catch (ex: IOException) {
        Toast.makeText(
            context,
            "SAVE_IMAGE_EXCEPTION_MESSAGE",
            Toast.LENGTH_SHORT
        ).show()
        null
    }

    val uri = photoFile?.let {
        FileProvider.getUriForFile(
        context,
        context.packageName + ".provider",
            it
        )
    }

    val storageDir: File = context.cacheDir
    val fileName = uri?.path
//    val file = File()
    updatePhoto("$storageDir/${fileName?.substringAfterLast("/")}")

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    Box(
        modifier = Modifier.width(width = widthBox.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.width(width = width.dp),
            onClick = {
                when (cameraPermissionState.hasPermission) {
                    true -> cameraLauncher?.launch(uri)
                    false -> cameraPermissionState.launchPermissionRequest()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(25),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
        ) {
            Image(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                painter = painterResource(id = imageId),
                contentDescription = description
            )
        }
    }
}

@Throws(IOException::class)
private fun createImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File = context.cacheDir
    return File.createTempFile(
        "JPEG_${timeStamp}",
        ".jpg",
        storageDir
    )
}

@Composable
fun TitleScreen(title: String) {
    Text(
        text = title,
        color = TextTitleColor,
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(600)
    )
}

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

@Preview(showBackground = true, widthDp = 400, heightDp = 848)
@Composable
fun DefaultPreview() {
//    Gallery(galleryViewModel.uri)
}
