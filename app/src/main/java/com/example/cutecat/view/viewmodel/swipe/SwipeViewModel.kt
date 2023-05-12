package com.example.cutecat.view.viewmodel.swipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.cat.CatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SwipeViewModel(private val catListRepository: CatListRepository): ViewModel() {

    val catPhoto = MutableLiveData<List<CatItem>>()


    fun getCatCheckNetwork(){
        viewModelScope.launch(Dispatchers.IO) {
            getRandomCat()
        }
    }

    suspend fun getRandomCat(){
        val result = catListRepository.getOneCat()
        catPhoto.postValue(result.body())
    }

}