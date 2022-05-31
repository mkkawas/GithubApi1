package com.example.githubapi1.services2


import com.example.githubapi1.modelsKoosa.GetReposModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("repos")
   suspend fun getRepos(
        @Query("page") page: Int
    ): List<GetReposModel>

    companion object {

        private const val url = "https://api.github.com/orgs/google/"


        fun getApiService() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}