package com.example.CuteCats.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cutecat.view.screens.FavouriteCatsFragment
import com.example.cutecat.view.screens.RandomCatListFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val numPages = 2

    override fun getItemCount(): Int {
        return numPages
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RandomCatListFragment()
            1 -> FavouriteCatsFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}