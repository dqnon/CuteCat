package com.example.cutecat.domain.usecase.room

import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem

class AddCatUseCase(private val roomFavouriteRepository: RoomFavouriteRepository) {

    suspend fun addCat(item: CatItem, onSuccess:() -> Unit){
        roomFavouriteRepository.addCat(item){}
    }
}