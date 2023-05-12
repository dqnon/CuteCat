package com.example.cutecat.domain.repository

import com.example.cutecat.model.cat.Cat
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.model.breeds.Breed
import com.example.cutecat.model.categories.Categories
import retrofit2.Response

interface CatListRepository {

    suspend fun getCatList(breed: String): Response<Cat>

    suspend fun getOneCat(): Response<List<CatItem>>

    suspend fun getBreeds(): Response<Breed>

    suspend fun getCategories(): Response<Categories>
}