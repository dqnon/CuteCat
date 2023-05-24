package com.example.cutecat.view.viewmodel.fullImage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.domain.DownloadImages
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FullImageViewModel(private val roomFavouriteRepository: RoomFavouriteRepository,
private val downloadImages: DownloadImages): ViewModel() {

    val allItemsCat = MutableLiveData<MutableList<CatItem>>()

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

    fun removeCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            roomFavouriteRepository.deleteCat(cat){}
        }

    fun addCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            roomFavouriteRepository.addCat(cat) {}
        }

    fun downloadImage(catItem: CatItem){
        viewModelScope.launch(Dispatchers.IO ) {
            val bitmapImage = downloadImages.getBitmapFromUrl(catItem.url)
            bitmapImage?.let { downloadImages.saveImageToGallery(it) }

        }
    }
}