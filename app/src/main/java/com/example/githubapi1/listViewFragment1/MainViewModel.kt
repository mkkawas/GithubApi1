package com.example.githubapi1.listViewFragment1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.githubapi1.services2.Api
import com.example.githubapi1.paging.ItemPagingSource


class MainViewModel(application: Application): AndroidViewModel(application) {
    private var apiService = Api.getApiService().create(Api::class.java)

    val listData = Pager(PagingConfig(pageSize = 10)) {
        ItemPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)



//    fun getData() : Flow<PagingData<GetReposModel>> {
//        return Pager(
//            config = PagingConfig(pageSize = 12),
//            pagingSourceFactory = {
//                ItemPagingSource(apiService)
//            }
//        ).flow.cachedIn(viewModelScope)
//    }
}