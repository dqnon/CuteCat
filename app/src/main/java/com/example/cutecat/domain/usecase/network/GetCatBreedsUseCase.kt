package com.example.cutecat.domain.usecase.network

import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.breeds.Breed
import retrofit2.Response

class GetCatBreedsUseCase(private val catListRepository: CatListRepository) {

    suspend fun executeCatBreeds(): Response<Breed> {
        return catListRepository.getBreeds()
    }
}