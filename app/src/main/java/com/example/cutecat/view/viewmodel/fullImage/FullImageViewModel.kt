package com.example.cutecat.view.viewmodel.fullImage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.domain.DownloadImages
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
class FullImageViewModel @Inject constructor(
    private val downloadImages: DownloadImages,
    private val getAllCatsUseCase: GetAllCatsUseCase,
    private val deleteCatUseCase: DeleteCatUseCase,
    private val addCatUseCase: AddCatUseCase): ViewModel() {

    val allItemsCat = MutableLiveData<MutableList<CatItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO ) {
            getAllCats()
        }
    }

    //???
    fun getAllCats(){
        val result = getAllCatsUseCase.getAllCats()
        allItemsCat.postValue(result)
    }

    fun removeCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            deleteCatUseCase.deleteCat(cat){}
        }

    fun addCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            addCatUseCase.addCat(cat){}
        }

    fun downloadImage(catItem: CatItem){
        viewModelScope.launch(Dispatchers.IO ) {
            val bitmapImage = downloadImages.getBitmapFromUrl(catItem.url)
            bitmapImage?.let { downloadImages.saveImageToGallery(it) }

        }
    }
}