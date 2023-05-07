package com.example.cutecat.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomCatViewModel(private val catListRepository: CatListRepository): ViewModel(){


    val resultCat = MutableLiveData<Cat>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCats()
        }
    }


    suspend fun getCats(){
        val result = catListRepository.getCat()
        resultCat.postValue(result.body())

    }

}