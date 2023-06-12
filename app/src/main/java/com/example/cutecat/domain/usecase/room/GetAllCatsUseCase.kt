package com.example.cutecat.domain.usecase.room

import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.model.cat.CatItem

class GetAllCatsUseCase(private val roomFavouriteRepository: RoomFavouriteRepository) {


    fun getAllCats(): MutableList<CatItem>{
        return roomFavouriteRepository.allCats
    }
}