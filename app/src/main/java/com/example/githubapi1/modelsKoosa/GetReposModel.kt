package com.example.githubapi1.modelsKoosa

data class GetReposModel(val id:String?, val name:String?, var description: String?, val owner: Owner?)
data class Owner(val avatar_url: String?)




