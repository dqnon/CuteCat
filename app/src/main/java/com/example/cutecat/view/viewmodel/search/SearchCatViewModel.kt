package com.example.cutecat.view.viewmodel.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.Utils.NetworkService
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.breeds.Breed
import com.example.cutecat.model.cat.Cat
import com.example.cutecat.model.categories.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchCatViewModel(private val catListRepository: CatListRepository,
                         private val networkService: NetworkService)
    : ViewModel(){


    val resultCat = MutableLiveData<Cat>()
    val resultBreeds = MutableLiveData<Breed>()
    val resultCategories = MutableLiveData<Categories>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBreeds()
            getCategories()
        }
    }

    suspend fun getCategories(){
        val result = catListRepository.getCategories()
        resultCategories.postValue(result.body())
    }

    suspend fun getBreeds(){
        val result = catListRepository.getBreeds()
        resultBreeds.postValue(result.body())
    }

    fun getDataCats(breed: String, categories: String){
        if (networkService.isNetworkAvailable()){
            viewModelScope.launch(Dispatchers.IO) {
                getCats(breed, categories)
            }
        } else {
            Log.d("MyLog", "NETWORK ERROR")
        }

    }


    suspend fun getCats(breed: String, categories: String){
        val result = catListRepository.getCatList(breed, categories)
        resultCat.postValue(result.body())

    }

}