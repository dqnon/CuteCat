package com.example.cutecat.view.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cutecat.Utils.getSerializable
import com.example.cutecat.databinding.ActivityFullImageBinding
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.view.viewmodel.fullImage.FullImageViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FullImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullImageBinding
    private val fullImageViewModel: FullImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fullImageViewModel = ViewModelProvider(this, FullImageViewModelFactory(this))
//            .get(FullImageViewModel::class.java)

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