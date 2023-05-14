package com.example.cutecat.view.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.Utils.getSerializable
import com.example.cutecat.data.db.toCatRoomItem
import com.example.cutecat.databinding.ActivityFullImageBinding
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.view.viewmodel.FullImage.FullImageViewModel
import com.example.cutecat.view.viewmodel.FullImage.FullImageViewModelFactory
import com.example.cutecat.view.viewmodel.search.SearchCatViewModel
import com.example.cutecat.view.viewmodel.search.SearchCatViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        Toast.makeText(this, "${catItem.url}", Toast.LENGTH_SHORT).show()
        Picasso.get().load(catItem.url).into(binding.imFullCat)



        binding.btFav.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                fullImageViewModel.addCatFavourite(catItem.toCatRoomItem()) {}
                fullImageViewModel.getAllCats()
            }
        }
    }
}