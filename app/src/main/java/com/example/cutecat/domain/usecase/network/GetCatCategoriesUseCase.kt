package com.example.cutecat.domain.usecase.network

import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.categories.Categories
import retrofit2.Response

class GetCatCategoriesUseCase(private val catListRepository: CatListRepository) {

    suspend fun executeCatCategories(): Response<Categories> {
        return catListRepository.getCategories()
    }
}