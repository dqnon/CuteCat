package com.example.cutecat.data.repository

import com.example.cutecat.data.api.CatApi
import com.example.cutecat.data.api.RetrofitHelper
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.model.Cat
import retrofit2.Response
import retrofit2.create

class CatListRepositoryImpl: CatListRepository {

    private val retrofit = RetrofitHelper.getInstance()

    private val catApi = retrofit.create(CatApi::class.java)

    override suspend fun getCat(): Response<Cat> {
        val catResult = catApi.getCats()
        return catResult
    }
}