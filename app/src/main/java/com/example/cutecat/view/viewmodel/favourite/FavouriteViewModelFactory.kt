package com.example.cutecat.view.viewmodel.favourite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.db.CatsDb
import com.example.cutecat.data.repository.RoomFavouriteRepositoryImpl

class FavouriteViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val database = CatsDb.getDb(context).getDao()

    private val roomFavouriteRepository = RoomFavouriteRepositoryImpl(database)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(
            roomFavouriteRepository = roomFavouriteRepository
        ) as T
    }

}