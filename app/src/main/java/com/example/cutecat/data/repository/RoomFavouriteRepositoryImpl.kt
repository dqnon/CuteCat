package com.example.cutecat.data.repository

import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.data.db.Dao
import com.example.cutecat.domain.repository.RoomFavouriteRepository

class RoomFavouriteRepositoryImpl(private val catDao: Dao): RoomFavouriteRepository {
    override val allCats: MutableList<CatRoomItem>
        get() = catDao.getAllItem()

    override suspend fun addCat(item: CatRoomItem, onSuccess: () -> Unit) {
        catDao.addFavourite(item)
        onSuccess()
    }

    override suspend fun deleteCat(item: CatRoomItem, onSuccess: () -> Unit) {
        catDao.deleteFavourite(item)
        onSuccess()
    }
}