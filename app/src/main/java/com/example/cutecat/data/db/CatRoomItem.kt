package com.example.cutecat.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats_fav")
data class CatRoomItem(
    @PrimaryKey
    val id: String,
    val url: String,
)