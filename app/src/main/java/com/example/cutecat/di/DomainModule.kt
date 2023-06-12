package com.example.cutecat.di

import com.example.cutecat.domain.repository.CatListRepository
import com.example.cutecat.domain.repository.RoomFavouriteRepository
import com.example.cutecat.domain.usecase.network.GetCatBreedsUseCase
import com.example.cutecat.domain.usecase.network.GetCatCategoriesUseCase
import com.example.cutecat.domain.usecase.network.GetCatListUseCase
import com.example.cutecat.domain.usecase.network.GetOneCatUseCase
import com.example.cutecat.domain.usecase.room.AddCatUseCase
import com.example.cutecat.domain.usecase.room.DeleteCatUseCase
import com.example.cutecat.domain.usecase.room.GetAllCatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllCatsUseCase(roomFavouriteRepository: RoomFavouriteRepository): GetAllCatsUseCase{
        return GetAllCatsUseCase(roomFavouriteRepository)
    }

    @Provides
    fun providesDeleteCatUseCase(roomFavouriteRepository: RoomFavouriteRepository): DeleteCatUseCase{
        return DeleteCatUseCase(roomFavouriteRepository)
    }

    @Provides
    fun provideAddCatUseCase(roomFavouriteRepository: RoomFavouriteRepository): AddCatUseCase{
        return AddCatUseCase(roomFavouriteRepository)
    }

    @Provides
    fun provideGetCatListUseCase(catListRepository: CatListRepository): GetCatListUseCase{
        return GetCatListUseCase(catListRepository)
    }

    @Provides
    fun providesGetCatCategoriesUseCase(catListRepository: CatListRepository): GetCatCategoriesUseCase{
        return GetCatCategoriesUseCase(catListRepository)
    }

    @Provides
    fun providesGetCatBreedsUseCase(catListRepository: CatListRepository): GetCatBreedsUseCase{
        return GetCatBreedsUseCase(catListRepository)
    }

    @Provides
    fun providesGetOneCatUseCase(catListRepository: CatListRepository): GetOneCatUseCase{
        return GetOneCatUseCase(catListRepository)
    }
}