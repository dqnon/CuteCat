package com.example.cutecat.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cutecat.model.cat.CatItem

@androidx.room.Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(item: CatItem)

    @Delete
    suspend fun deleteFavourite(item: CatItem)

    @Query("SELECT * FROM cats_fav")
    fun getAllItem(): MutableList<CatItem>
}