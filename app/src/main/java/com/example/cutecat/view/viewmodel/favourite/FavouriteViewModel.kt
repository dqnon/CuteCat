package com.example.cutecat.view.viewmodel.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.domain.usecase.room.AddCatUseCase
import com.example.cutecat.domain.usecase.room.DeleteCatUseCase
import com.example.cutecat.domain.usecase.room.GetAllCatsUseCase
import com.example.cutecat.model.cat.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(private val getAllCatsUseCase: GetAllCatsUseCase): ViewModel() {

    val allItemsCat = MutableLiveData<MutableList<CatItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO ) {
            getAllCats()
        }
    }

    fun getAllCats(){
        val result = getAllCatsUseCase.getAllCats()
        allItemsCat.postValue(result)
    }

}