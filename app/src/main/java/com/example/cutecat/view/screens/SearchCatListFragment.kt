package com.example.cutecat.view.screens


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cutecat.databinding.FragmentSearchCatListBinding
import com.example.cutecat.model.breeds.BreedItem
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.view.adapters.CatAdapter
import com.example.cutecat.view.adapters.BreedAdapter
import com.example.cutecat.view.viewmodel.search.SearchCatViewModel
import com.example.cutecat.view.viewmodel.search.SearchCatViewModelFactory


class SearchCatListFragment : Fragment(), CatAdapter.Listener {

    private lateinit var binding: FragmentSearchCatListBinding
    lateinit var catAdapter: CatAdapter
    lateinit var searchCatViewModel: SearchCatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchCatViewModel = ViewModelProvider(this, SearchCatViewModelFactory(requireContext()))
            .get(SearchCatViewModel::class.java)

        searchCatViewModel.resultCat.observe(viewLifecycleOwner, Observer {
            Log.d("MyLog", "$it")

            catAdapter = CatAdapter(this)
            binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcView.adapter = catAdapter

            catAdapter.submitList(it)

        })

        searchCatViewModel.resultBreeds.observe(viewLifecycleOwner, Observer {
            val spinnerArray: MutableList<BreedItem> = ArrayList()
            spinnerArray.add(BreedItem("", ""))
            Log.d("BreedLog", it[0].name)
            it.forEach { breed ->
                spinnerArray.add(breed)
            }

            val adapterBreed = BreedAdapter(requireContext(), spinnerArray)
            binding.spinnerBreed.adapter = adapterBreed

        })

        binding.spinnerBreed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.getItemAtPosition(position) as BreedItem
                val breedId = item.id
                searchCatViewModel.getDataCats(breedId)

                //refresh button
                binding.floatingActionButton.setOnClickListener {
                    searchCatViewModel.getDataCats(breedId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


    }

    override fun onClick(cat: CatItem) {
        //Toast.makeText(requireContext(), "${cat.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), FullImageActivity::class.java)

        intent.putExtra("image_url", cat)
        requireContext().startActivity(intent)
    }


}