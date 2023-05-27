package com.example.cutecat.view.viewmodel.swipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.domain.DownloadImages
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SwipeViewModel(
    private val catListRepository: CatListRepository,
    private val roomFavouriteRepository: RoomFavouriteRepository,
    private val downloadImages: DownloadImages
): ViewModel() {

    val catPhoto = MutableLiveData<List<CatItem>>()

    init {
        getCatCheckNetwork()
    }

    fun addCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            roomFavouriteRepository.addCat(cat) {}
        }

    fun getCatCheckNetwork(){
        viewModelScope.launch(Dispatchers.IO) {
            getRandomCat()
        }
    }

    suspend fun getRandomCat(){
        val result = catListRepository.getOneCat()
        catPhoto.postValue(result.body())
    }

    fun downloadImage(catItem: CatItem){
        viewModelScope.launch(Dispatchers.IO ) {
            val bitmapImage = downloadImages.getBitmapFromUrl(catItem.url)
            bitmapImage?.let { downloadImages.saveImageToGallery(it) }

        }
    }

}