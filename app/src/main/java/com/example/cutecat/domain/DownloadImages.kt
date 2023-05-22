package com.example.cutecat.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Environment
import android.provider.MediaStore
import com.example.cutecat.model.cat.CatItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*


class DownloadImages(private val context: Context) {

    fun downloadImage(catItem: CatItem){
        val url = URL(catItem.url)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        val filename = "image${catItem.id}.png"
        val dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        val file = File(dirPath, filename)

        val outStream = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()

        MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, file.name, file.name)
    }

    fun downloadImage2(catItem: CatItem){
        
    }

}