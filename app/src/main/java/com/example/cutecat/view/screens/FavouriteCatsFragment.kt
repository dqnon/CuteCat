package com.example.cutecat.view.screens
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cutecat.databinding.FragmentFavouriteCatsBinding
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.view.adapters.CatAdapter
import com.example.cutecat.view.viewmodel.favourite.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteCatsFragment : Fragment(), CatAdapter.Listener {

    private lateinit var binding: FragmentFavouriteCatsBinding
    private val favouriteCatViewModel: FavouriteViewModel by viewModels()
    lateinit var catAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteCatsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        favouriteCatViewModel = ViewModelProvider(this, FavouriteViewModelFactory(requireContext()))
//            .get(FavouriteViewModel::class.java)

        favouriteCatViewModel.allItemsCat.observe(viewLifecycleOwner, Observer {
            Log.d("FavLog", "$it")
            catAdapter = CatAdapter(this)

            binding.rcViewFav.layoutManager = GridLayoutManager(requireContext(), 2)

            binding.rcViewFav.adapter = catAdapter

            catAdapter.submitList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            favouriteCatViewModel.getAllCats()
        }
    }

    override fun onClick(cat: CatItem) {
        //Toast.makeText(requireContext(), "click ${cat.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), FullImageActivity::class.java)
        intent.putExtra("image_url", cat)
        requireContext().startActivity(intent)
    }


}