package com.example.cutecat.domain.repository

import com.example.cutecat.model.Cat
import retrofit2.Response

interface CatListRepository {

    suspend fun getCat(): Response<Cat>
}