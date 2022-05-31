package com.example.githubapi1.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubapi1.modelsKoosa.GetReposModel
import com.example.githubapi1.services2.Api
import retrofit2.HttpException
import java.io.IOException

class ItemPagingSource(private val apiService: Api) : PagingSource<Int, GetReposModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetReposModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getRepos(page)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page -1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (exception: IOException) {
            Log.d("pagingError",exception.message.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d("pagingError",exception.message.toString())
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GetReposModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}