package com.example.cutecat.domain.usecase.network

import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.cat.CatItem
import retrofit2.Response

class GetOneCatUseCase(private val catListRepository: CatListRepository) {

    suspend fun getOneCat(): Response<List<CatItem>>{
        return catListRepository.getOneCat()
    }
}