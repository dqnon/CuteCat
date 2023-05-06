package com.example.cutecat.view.screens


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.CuteCats.view.adapters.ViewPagerAdapter
import com.example.cutecat.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
                tab, pos ->
            when(pos){
                0 -> tab.text = "Random"
                1 -> tab.text = "Favourites"
            }
        }.attach()
    }
}