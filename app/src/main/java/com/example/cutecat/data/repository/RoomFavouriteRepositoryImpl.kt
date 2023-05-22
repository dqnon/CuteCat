package com.example.cutecat.data.repository

import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.data.db.Dao
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem

class RoomFavouriteRepositoryImpl(private val catDao: Dao): RoomFavouriteRepository {
    override val allCats: MutableList<CatItem>
        get() = catDao.getAllItem()

    override suspend fun addCat(item: CatItem, onSuccess: () -> Unit) {
        catDao.addFavourite(item)
        onSuccess()
    }

    override suspend fun deleteCat(item: CatItem, onSuccess: () -> Unit) {
        catDao.deleteFavourite(item)
        onSuccess()
    }
}