package com.example.cutecat.data.api

import com.example.cutecat.model.Cat
import retrofit2.Response
import retrofit2.http.GET

const val API_KEY = "live_Lqg4bKtV0ok0wC8rb9mNJxMsgxrUuukkjyUpwRXJwUj0VBvXIJGlLlTsGxSRRfRr"

interface CatApi {
    //https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=REPLACE_ME



    @GET("/v1/images/search?limit=10&breed_ids=beng&api_key=$API_KEY")
    suspend fun getCats(): Response<Cat>
}