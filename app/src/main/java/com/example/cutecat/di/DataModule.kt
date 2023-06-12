package com.example.cutecat.di

import android.content.Context
import com.example.cutecat.Utils.NetworkService
import com.example.cutecat.data.db.CatsDb
import com.example.cutecat.data.db.Dao
import com.example.cutecat.data.repository.CatListRepositoryImpl
import com.example.cutecat.data.repository.RoomFavouriteRepositoryImpl
import com.example.cutecat.domain.DownloadImages
import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Dao {
        return CatsDb.getDb(context).getDao()
    }

    @Provides
    fun provideRoomFavouriteRepository(database: Dao): RoomFavouriteRepository{
        return RoomFavouriteRepositoryImpl(database)
    }

    @Provides
    fun provideDownloadImages(@ApplicationContext context: Context): DownloadImages {
        return DownloadImages(context)
    }

    @Provides
    fun provideCatListRepository(): CatListRepository{
        return CatListRepositoryImpl()
    }

    @Provides
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService{
        return NetworkService(context)
    }
}