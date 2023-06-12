package com.example.cutecat.view.viewmodel.swipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutecat.Utils.NetworkService
import com.example.cutecat.domain.DownloadImages
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.domain.usecase.network.GetOneCatUseCase
import com.example.cutecat.domain.usecase.room.AddCatUseCase
import com.example.cutecat.domain.usecase.room.DeleteCatUseCase
import com.example.cutecat.model.cat.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwipeViewModel @Inject constructor(
    private val downloadImages: DownloadImages,
    private val networkService: NetworkService,
    private val addCatUseCase: AddCatUseCase,
    private val getOneCatUseCase: GetOneCatUseCase
): ViewModel() {

    val catPhoto = MutableLiveData<List<CatItem>>()

    init {
        getCatCheckNetwork()
    }

    fun addCatFavourite(cat: CatItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO ) {
            addCatUseCase.addCat(cat){}
        }

    fun getCatCheckNetwork(){
        if (networkService.isNetworkAvailable()){
            viewModelScope.launch(Dispatchers.IO) {
                getRandomCat()
            }
        } else {
            networkService.toastError()
        }
    }

    suspend fun getRandomCat(){
        val result = getOneCatUseCase.getOneCat()
        catPhoto.postValue(result.body())
    }

    fun downloadImage(catItem: CatItem){
        viewModelScope.launch(Dispatchers.IO ) {
            val bitmapImage = downloadImages.getBitmapFromUrl(catItem.url)
            bitmapImage?.let { downloadImages.saveImageToGallery(it) }

        }
    }

}