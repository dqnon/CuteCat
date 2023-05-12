package com.example.cutecat.view.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cutecat.R
import com.example.cutecat.databinding.ActivityFullImageBinding
import com.squareup.picasso.Picasso

class FullImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("image_url")
        Toast.makeText(this, "${url}", Toast.LENGTH_SHORT).show()
        Picasso.get().load(url).into(binding.imFullCat)
    }
}