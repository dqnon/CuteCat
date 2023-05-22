package com.example.cutecat.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cutecat.model.cat.CatItem

@Database(entities = [CatItem::class], version = 1)
abstract class CatsDb: RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: Context): CatsDb{
            return Room.databaseBuilder(context, CatsDb::class.java, "cats.db").build()
        }
    }
}