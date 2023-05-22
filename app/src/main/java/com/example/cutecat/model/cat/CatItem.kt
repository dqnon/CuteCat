package com.example.cutecat.model.cat

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cats_fav")
data class CatItem(
    //val height: Int,
    @PrimaryKey
    val id: String,
    val url: String,
    //val width: Int
    var isFavourite: Boolean = false
): Serializable