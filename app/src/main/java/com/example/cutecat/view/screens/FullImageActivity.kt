package com.example.cutecat.view.screens

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.Utils.getSerializable
import com.example.cutecat.data.db.toCatRoomItem
import com.example.cutecat.databinding.ActivityFullImageBinding
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.view.viewmodel.fullImage.FullImageViewModel
import com.example.cutecat.view.viewmodel.fullImage.FullImageViewModelFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.time.LocalTime
import java.util.*
import kotlin.random.Random

class FullImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullImageBinding
    lateinit var fullImageViewModel: FullImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullImageViewModel = ViewModelProvider(this, FullImageViewModelFactory(this))
            .get(FullImageViewModel::class.java)

        //fullscreen
        val catItem = intent.getSerializable("image_url", CatItem::class.java)
        //Toast.makeText(this, "${catItem.url}", Toast.LENGTH_SHORT).show()
        Picasso.get().load(catItem.url).into(binding.imFullCat)

        if(catItem.isFavourite){
            binding.btFav.visibility = View.INVISIBLE
        } else{
            binding.btRemove.visibility = View.INVISIBLE
        }



        binding.btFav.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                catItem.isFavourite = true
                fullImageViewModel.addCatFavourite(catItem) {}
                fullImageViewModel.getAllCats()
            }
            Toast.makeText(this, "Image added to favorites", Toast.LENGTH_SHORT).show()
        }

        binding.btRemove.setOnClickListener {
            fullImageViewModel.removeCatFavourite(catItem){}
            Toast.makeText(this, "Image removed from favourites", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btDownload.setOnClickListener {
            fullImageViewModel.downloadImage(catItem)
            Toast.makeText(this, "Image downloaded", Toast.LENGTH_SHORT).show()


        }


    }

}