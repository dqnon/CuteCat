package com.example.cutecat.model.cat

import java.io.Serializable

data class CatItem(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
): Serializable