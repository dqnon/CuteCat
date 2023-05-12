package com.example.cutecat.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.databinding.FragmentSwipeBinding
import com.example.cutecat.view.viewmodel.swipe.SwipeViewModel
import com.example.cutecat.view.viewmodel.swipe.SwipeViewModelFactory
import com.squareup.picasso.Picasso

class SwipeFragment : Fragment() {

    private lateinit var binding: FragmentSwipeBinding
    lateinit var swipeCatViewModel: SwipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSwipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeCatViewModel = ViewModelProvider(this, SwipeViewModelFactory())
            .get(SwipeViewModel::class.java)

        swipeCatViewModel.catPhoto.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it[0].url).into(binding.imageView)
        })

        binding.btNext.setOnClickListener {
            swipeCatViewModel.getCatCheckNetwork()
        }
    }

}