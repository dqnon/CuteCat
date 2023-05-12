package com.example.cutecat.view.viewmodel.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutecat.Utils.NetworkService
import com.example.cutecat.data.repository.CatListRepositoryImpl

class SearchCatViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    private val catListRepository = CatListRepositoryImpl()

    private val networkService = NetworkService(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchCatViewModel(
            catListRepository = catListRepository,
            networkService = networkService
        ) as T
    }

}