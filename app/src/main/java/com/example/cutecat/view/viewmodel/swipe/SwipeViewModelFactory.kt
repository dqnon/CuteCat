package com.example.cutecat.view.viewmodel.swipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.db.CatsDb
import com.example.cutecat.data.repository.CatListRepositoryImpl
import com.example.cutecat.data.repository.RoomFavouriteRepositoryImpl
import com.example.cutecat.domain.DownloadImages
import com.example.cutecat.view.viewmodel.search.SearchCatViewModel

class SwipeViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val catListRepository = CatListRepositoryImpl()

    //database
    private val database = CatsDb.getDb(context).getDao()
    private val roomFavouriteRepository = RoomFavouriteRepositoryImpl(database)

    //download
    private val downloadImages = DownloadImages(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SwipeViewModel(
            catListRepository = catListRepository,
            roomFavouriteRepository = roomFavouriteRepository,
            downloadImages = downloadImages
        ) as T
    }
}