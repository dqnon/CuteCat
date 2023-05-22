package com.example.cutecat.domain.repository

import com.example.cutecat.data.db.CatRoomItem
import com.example.cutecat.model.cat.CatItem

interface RoomFavouriteRepository {

    val allCats: MutableList<CatItem>

    suspend fun addCat(item: CatItem, onSuccess:() -> Unit)

    suspend fun deleteCat(item: CatItem, onSuccess:() -> Unit)
}