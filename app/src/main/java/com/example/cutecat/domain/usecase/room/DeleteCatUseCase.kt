package com.example.cutecat.domain.usecase.room

import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem

class DeleteCatUseCase(private val roomFavouriteRepository: RoomFavouriteRepository) {

    suspend fun deleteCat(item: CatItem, onSuccess:() -> Unit){
        roomFavouriteRepository.deleteCat(item){}
    }

}