package com.example.cutecat.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.data.repository.CatListRepositoryImpl

class RandomCatViewModelFactory(): ViewModelProvider.Factory {

    private val catListRepository = CatListRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomCatViewModel(
            catListRepository = catListRepository
        ) as T
    }

}