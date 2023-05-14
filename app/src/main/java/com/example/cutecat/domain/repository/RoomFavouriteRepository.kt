package com.example.cutecat.domain.repository

import com.example.cutecat.data.db.CatRoomItem

interface RoomFavouriteRepository {

    val allCats: MutableList<CatRoomItem>

    suspend fun addCat(item: CatRoomItem, onSuccess:() -> Unit)

    suspend fun deleteCat(item: CatRoomItem, onSuccess:() -> Unit)
}