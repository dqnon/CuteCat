package com.example.cutecat.view.viewmodel.FullImage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FullImageViewModel(private val roomFavouriteRepository: RoomFavouriteRepository): ViewModel() {

    val allItemsCat = MutableLiveData<MutableList<CatRoomItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO ) {
            getAllCats()
        }
    }

    //???
    fun getAllCats(){
        val result = roomFavouriteRepository.allCats
        allItemsCat.postValue(result)
    }

    fun addCatFavourite(cat: CatRoomItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            roomFavouriteRepository.addCat(cat) {}
        }
}