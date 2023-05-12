package com.example.cutecat.view.viewmodel.swipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.repository.CatListRepositoryImpl
import com.example.cutecat.view.viewmodel.search.SearchCatViewModel

class SwipeViewModelFactory: ViewModelProvider.Factory {

    private val catListRepository = CatListRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SwipeViewModel(
            catListRepository = catListRepository
        ) as T
    }
}