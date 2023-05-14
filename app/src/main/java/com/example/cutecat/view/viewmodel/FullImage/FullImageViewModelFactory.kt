package com.example.cutecat.view.viewmodel.FullImage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.db.CatsDb
import com.example.cutecat.data.repository.RoomFavouriteRepositoryImpl

class FullImageViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val database = CatsDb.getDb(context).getDao()

    private val roomFavouriteRepository = RoomFavouriteRepositoryImpl(database)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FullImageViewModel(
            roomFavouriteRepository = roomFavouriteRepository
        ) as T
    }
}