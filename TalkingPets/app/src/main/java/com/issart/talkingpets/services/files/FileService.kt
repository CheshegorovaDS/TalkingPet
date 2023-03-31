package com.issart.talkingpets.services.files

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FileService @Inject constructor(
    private val context: Context
) {
    @Throws(IOException::class)
    fun getUrlForBitmap(image: Bitmap): Uri {
        val photoFile: File = createImageFile()
        return when(saveImageToFile(image, photoFile)) {
            true -> FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                photoFile
            )
            false -> throw IOException("Error saving image to file")
        }
    }

    @TargetApi(Build.VERSION_CODES.S)
    fun loadImageFromFile(uri: Uri): Bitmap {
        return when (Build.VERSION.SDK_INT < 28) {
            true -> {
                MediaStore.Images.Media.getBitmap(
                    context.contentResolver,
                    uri
                )
            }
            false -> {
                val source = ImageDecoder.createSource(
                    context.contentResolver,
                    uri
                )
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = context.cacheDir
        return File.createTempFile(
            "JPEG_${timeStamp}",
            ".jpg",
            storageDir
        )
    }

    private fun saveImageToFile(image: Bitmap, file: File): Boolean {
        val outputStream = FileOutputStream(file)
        val result = image.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.flush()
        outputStream.close()

        return result
    }
}