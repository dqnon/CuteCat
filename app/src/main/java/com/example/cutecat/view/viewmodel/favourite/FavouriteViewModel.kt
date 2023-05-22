package com.example.cutecat.view.viewmodel.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(private val roomFavouriteRepository: RoomFavouriteRepository): ViewModel() {

    val allItemsCat = MutableLiveData<MutableList<CatItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO ) {
            getAllCats()
        }
    }


    fun getAllCats(){
        val result = roomFavouriteRepository.allCats
        allItemsCat.postValue(result)
    }

}