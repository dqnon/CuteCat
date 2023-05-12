package com.example.cutecat.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cutecat.view.screens.FavouriteCatsFragment
import com.example.cutecat.view.screens.SearchCatListFragment
import com.example.cutecat.view.screens.SwipeFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val numPages = 3

    override fun getItemCount(): Int {
        return numPages
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SwipeFragment()
            1 -> SearchCatListFragment()
            2 -> FavouriteCatsFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}