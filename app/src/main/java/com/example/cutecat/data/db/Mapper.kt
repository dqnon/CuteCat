package com.example.cutecat.data.db

import com.example.cutecat.model.cat.CatItem

fun CatItem.toCatRoomItem() = CatRoomItem(
    id = id,
    url = url
)