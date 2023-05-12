package com.example.cutecat.data.repository

import com.example.cutecat.data.api.CatApi
import com.example.cutecat.data.api.RetrofitHelper
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.cat.Cat
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.model.breeds.Breed
import com.example.cutecat.model.categories.Categories
import retrofit2.Response

class CatListRepositoryImpl: CatListRepository {

    private val retrofit = RetrofitHelper.getInstance()

    private val catApi = retrofit.create(CatApi::class.java)

    override suspend fun getCatList(breed: String): Response<Cat> {
        val catResult = catApi.getCatsList(breed)
        return catResult
    }

    override suspend fun getOneCat(): Response<List<CatItem>> {
        val catResult = catApi.getCat()
        return catResult
    }

    override suspend fun getBreeds(): Response<Breed> {
        val breedResult = catApi.getBreeds()
        return breedResult
    }

    override suspend fun getCategories(): Response<Categories> {
        val categoriesResult = catApi.getCategories()
        return categoriesResult
    }
}