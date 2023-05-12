package com.example.cutecat.data.api

import com.example.cutecat.model.cat.Cat
import com.example.cutecat.model.cat.CatItem
import com.example.cutecat.model.breeds.Breed
import com.example.cutecat.model.categories.Categories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "live_Lqg4bKtV0ok0wC8rb9mNJxMsgxrUuukkjyUpwRXJwUj0VBvXIJGlLlTsGxSRRfRr"

interface CatApi {
    //https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=REPLACE_ME



    @GET("/v1/images/search?limit=10&api_key=$API_KEY")
    suspend fun getCatsList(@Query("breed_ids") breed: String): Response<Cat>

    @GET("/v1/images/search")
    suspend fun getCat(): Response<List<CatItem>>

    @GET("/v1/breeds?api_key=$API_KEY")
    suspend fun getBreeds(): Response<Breed>

    @GET("/v1/categories")
    suspend fun getCategories(): Response<Categories>
}