package com.example.cutecat.view.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.Utils.NetworkService
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.domain.usecase.network.GetCatBreedsUseCase
import com.example.cutecat.domain.usecase.network.GetCatCategoriesUseCase
import com.example.cutecat.domain.usecase.network.GetCatListUseCase
import com.example.cutecat.model.breeds.Breed
import com.example.cutecat.model.cat.Cat
import com.example.cutecat.model.categories.Categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCatViewModel
@Inject constructor(private val networkService: NetworkService,
                    private val getCatListUseCase: GetCatListUseCase,
                    private val getCatBreedsUseCase: GetCatBreedsUseCase,
                    private val getCatCategoriesUseCase: GetCatCategoriesUseCase
)
    : ViewModel(){


    val resultCat = MutableLiveData<Cat>()
    val resultBreeds = MutableLiveData<Breed>()
    val resultCategories = MutableLiveData<Categories>()

    init {

        if (networkService.isNetworkAvailable()){
            viewModelScope.launch(Dispatchers.IO) {
                getBreeds()
                getCategories()
            }
        } else {
            networkService.toastError()
        }

    }

    suspend fun getCategories(){
        val result = getCatCategoriesUseCase.executeCatCategories()
        resultCategories.postValue(result.body())
    }

    suspend fun getBreeds(){
        val result = getCatBreedsUseCase.executeCatBreeds()
        resultBreeds.postValue(result.body())
    }

    fun getDataCats(breed: String, categories: String){
        if (networkService.isNetworkAvailable()){
            viewModelScope.launch(Dispatchers.IO) {
                getCats(breed, categories)
            }
        } else {
            networkService.toastError()
        }

    }


    suspend fun getCats(breed: String, categories: String){
        val result = getCatListUseCase.executeCatList(breed, categories)
        resultCat.postValue(result.body())

    }

}