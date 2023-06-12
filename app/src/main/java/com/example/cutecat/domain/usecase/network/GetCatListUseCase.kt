package com.example.cutecat.domain.usecase.network

import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.cat.Cat
import retrofit2.Response

class GetCatListUseCase(private val catListRepository: CatListRepository) {

    suspend fun executeCatList(breed: String, categories: String): Response<Cat> {
        return catListRepository.getCatList(breed, categories)
    }
}