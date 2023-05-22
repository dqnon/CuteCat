package com.example.cutecat.view.viewmodel.fullImage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.db.CatsDb
import com.example.cutecat.data.repository.RoomFavouriteRepositoryImpl
import com.example.cutecat.domain.DownloadImages

class FullImageViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val database = CatsDb.getDb(context).getDao()
    private val roomFavouriteRepository = RoomFavouriteRepositoryImpl(database)

    //download
    private val downloadImages = DownloadImages(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FullImageViewModel(
            roomFavouriteRepository = roomFavouriteRepository,
            downloadImages = downloadImages
        ) as T
    }
}