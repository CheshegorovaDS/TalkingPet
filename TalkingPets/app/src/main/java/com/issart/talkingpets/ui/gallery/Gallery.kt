package com.issart.talkingpets.ui.gallery

import android.annotation.SuppressLint
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Purple
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.common.gridLayout.HORIZONTAL_PADDING_GRID_LAYOUT
import com.issart.talkingpets.ui.common.gridLayout.ImageGridLayout
import com.issart.talkingpets.ui.common.gridLayout.VERTICAL_PADDING_GRID_LAYOUT
import com.issart.talkingpets.ui.common.texts.TitleText
import com.issart.talkingpets.ui.editor.EditorViewModel
import com.issart.talkingpets.ui.utils.StringCallback
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Gallery(
    onChoosePhoto: (TalkingPetsScreen) -> Unit,
    galleryViewModel: GalleryViewModel = hiltViewModel(),
    editorViewModel: EditorViewModel = hiltViewModel()
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val photoUri = galleryViewModel.uri.observeAsState(initial = null)
    val updatePhoto = galleryViewModel::setPhotoUri

    if (bitmap == null) {
        photoUri.value?.let {
            val bitmapFromFile = BitmapFactory.decodeFile(photoUri.value)
            if (bitmapFromFile != null) {
                bitmap = bitmapFromFile
            }
        }
    }

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
            AnimalGridLayout(updatePhoto)
        }
        else -> {
            bitmap?.let { editorViewModel.setEditorBitmap(it) }
            updatePhoto(null)
            onChoosePhoto(TalkingPetsScreen.EDITOR)
        }
    }

}

@Composable
fun GalleryTitleText() = TitleText(
    title = stringResource(id = R.string.create_first_animation)
)

@Composable
fun GalleryButtonsRow(
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    updatePhoto: StringCallback
) = Row(
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

@Composable
fun AnimalGridLayout(updatePhoto: StringCallback) {
    val context = LocalContext.current
    ImageGridLayout(
        data = getAnimalIdList(),
        onClickCallback = { animalImageId ->
            setPhotoFromList(
                context = context,
                animalImageId = animalImageId,
                updatePhoto = updatePhoto
            )
        },
        contentPadding = PaddingValues(
            vertical = VERTICAL_PADDING_GRID_LAYOUT.dp,
            horizontal = HORIZONTAL_PADDING_GRID_LAYOUT.dp
        )
    )
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
                    true -> {
                        val uri = getPhotoUri(context)
                        val storageDir: File = context.cacheDir
                        val fileName = uri?.path
                        updatePhoto("$storageDir/${fileName?.substringAfterLast("/")}")

                        cameraLauncher?.launch(uri)
                    }
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

private fun updateGalleryPhoto(
    filePath: String,
    context: Context,
    animalPhotoId: Int,
    updatePhoto: StringCallback
) {
    val bitmap = BitmapFactory.decodeResource(context.resources, animalPhotoId)
    val file = File(filePath)

    try {
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()
        updatePhoto(filePath)
    } catch (e:IOException) {
        showToast(context, e.message.toString())
    }
}

private fun getPhotoUri(context: Context): Uri? {
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

    return uri
}

@SuppressLint("SimpleDateFormat")
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

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

private fun setPhotoFromList(context: Context, animalImageId: Int, updatePhoto: StringCallback) {
    val uri = getPhotoUri(context)
    val storageDir: File = context.cacheDir
    val fileName = uri?.path
    val filePath = "$storageDir/${fileName?.substringAfterLast("/")}"
    updateGalleryPhoto(filePath, context, animalImageId, updatePhoto)
}

@Preview(showBackground = true, widthDp = 400, heightDp = 848)
@Composable
fun DefaultPreview() {
//    Gallery(galleryViewModel.uri)
}
