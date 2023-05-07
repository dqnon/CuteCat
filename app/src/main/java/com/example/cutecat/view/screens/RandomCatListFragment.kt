package com.example.cutecat.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cutecat.R
import com.example.cutecat.databinding.FragmentFavouriteCatsBinding
import com.example.cutecat.databinding.FragmentRandomCatListBinding
import com.example.cutecat.view.adapters.CatAdapter
import com.example.cutecat.view.viewmodel.RandomCatViewModel
import com.example.cutecat.view.viewmodel.RandomCatViewModelFactory


class RandomCatListFragment : Fragment() {

    private lateinit var binding: FragmentRandomCatListBinding
    lateinit var catAdapter: CatAdapter
    lateinit var randomCatViewModel: RandomCatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRandomCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomCatViewModel = ViewModelProvider(this, RandomCatViewModelFactory())
            .get(RandomCatViewModel::class.java)

        randomCatViewModel.resultCat.observe(viewLifecycleOwner, Observer {
            Log.d("MyLog", "${it}")

            catAdapter = CatAdapter()
            binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcView.adapter = catAdapter

            catAdapter.submitList(it)

        })
    }


}