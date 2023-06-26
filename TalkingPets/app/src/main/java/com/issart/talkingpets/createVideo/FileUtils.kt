package com.issart.talkingpets.createVideo

import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.util.Log
import androidx.annotation.RawRes
import androidx.core.content.FileProvider
import java.io.File

object FileUtils {
    private val TAG = FileUtils::class.java.simpleName
    private const val MEDIA_FILE_PATH = "media"
    private const val FILE_AUTHORITY = "com.issart.talkingpets.createVideo.fileprovider"

    fun getFileDescriptor(
        context: Context,
        @RawRes rawAudioResource: Int
    ): AssetFileDescriptor {
        return context.resources.openRawResourceFd(rawAudioResource)
    }

    fun getVideoFile(context: Context, fileName: String): File {
        return getVideoFile(context, MEDIA_FILE_PATH, fileName)
    }

    private fun getVideoFile(
        context: Context,
        fileDir: String,
        fileName: String
    ): File {
        val mediaFolder = File(context.filesDir, fileDir)
        // Create the directory if it does not exist
        if (!mediaFolder.exists()) mediaFolder.mkdirs()
        Log.d(TAG, "Got folder at: " + mediaFolder.absolutePath)
        val file = File(mediaFolder, fileName)
        Log.d(TAG, "Got file at: " + file.absolutePath)
        return file
    }

    @JvmOverloads
    fun shareVideo(context: Context, file: File,
                   mimeType: String, fileAuthority: String = FILE_AUTHORITY): Boolean {
        if (!file.exists()) {
            return false
        }
        Log.d(TAG, "Found file at " + file.absolutePath)
        val uri = FileProvider.getUriForFile(context, fileAuthority, file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = mimeType
        context.startActivity(intent)
        return true
    }

}
