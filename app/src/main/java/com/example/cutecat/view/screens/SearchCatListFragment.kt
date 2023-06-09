package com.example.cutecat.view.screens


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cutecat.databinding.FragmentSearchCatListBinding
import com.example.cutecat.model.breeds.BreedItem
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.model.categories.CategoriesItem
import com.example.cutecat.view.adapters.CatAdapter
import com.example.cutecat.view.adapters.BreedAdapter
import com.example.cutecat.view.adapters.CategoriesSpinnerAdapter
import com.example.cutecat.view.viewmodel.search.SearchCatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCatListFragment : Fragment(), CatAdapter.Listener {

    private lateinit var binding: FragmentSearchCatListBinding
    lateinit var catAdapter: CatAdapter
    private val searchCatViewModel: SearchCatViewModel by viewModels()
    val spinnerOptionList: MutableList<String> = mutableListOf("", "")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        searchCatViewModel = ViewModelProvider(this, SearchCatViewModelFactory(requireContext()))
//            .get(SearchCatViewModel::class.java)

        searchCatViewModel.resultCat.observe(viewLifecycleOwner, Observer {
            Log.d("MyLog", "$it")

            catAdapter = CatAdapter(this)
            binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcView.adapter = catAdapter

            catAdapter.submitList(it)

            if(it.isEmpty()){
                Toast.makeText(requireContext(), "Нет результатов по заданным параметрам", Toast.LENGTH_LONG).show()
            }

        })

        searchCatViewModel.resultCategories.observe(viewLifecycleOwner, Observer {
            val spinnerCategoriesArray: MutableList<CategoriesItem> = ArrayList()
            spinnerCategoriesArray.add(CategoriesItem("", ""))
            it.forEach { breed ->
                spinnerCategoriesArray.add(breed)
            }

            val adapterCategories = CategoriesSpinnerAdapter(requireContext(), spinnerCategoriesArray)
            binding.spinnerCategory.adapter = adapterCategories
        })

        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.getItemAtPosition(position) as CategoriesItem
                //val categoriesId = item.id
                spinnerOptionList[1] = item.id.toString()
                searchCatViewModel.getDataCats(spinnerOptionList[0], spinnerOptionList[1])

                //refresh button
                binding.floatingActionButton.setOnClickListener {
                    searchCatViewModel.getDataCats(spinnerOptionList[0], spinnerOptionList[1])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        searchCatViewModel.resultBreeds.observe(viewLifecycleOwner, Observer {
            val spinnerBreedArray: MutableList<BreedItem> = ArrayList()
            spinnerBreedArray.add(BreedItem("", ""))
            Log.d("BreedLog", it[0].name)
            it.forEach { breed ->
                spinnerBreedArray.add(breed)
            }

            val adapterBreed = BreedAdapter(requireContext(), spinnerBreedArray)
            binding.spinnerBreed.adapter = adapterBreed

        })

        binding.spinnerBreed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.getItemAtPosition(position) as BreedItem
                spinnerOptionList[0] = item.id
                searchCatViewModel.getDataCats(spinnerOptionList[0], spinnerOptionList[1])
                Log.d("SpinnerLog", "$spinnerOptionList")

                //refresh button
                binding.floatingActionButton.setOnClickListener {
                    searchCatViewModel.getDataCats(spinnerOptionList[0], spinnerOptionList[1])
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