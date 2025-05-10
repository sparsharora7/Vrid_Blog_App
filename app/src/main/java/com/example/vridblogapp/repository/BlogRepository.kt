package com.example.vridblogapp.repository

import com.example.vridblogapp.data.BlogApiService
import com.example.vridblogapp.model.BlogPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BlogRepository {
    private val api: BlogApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlogApiService::class.java)
    }

    suspend fun getBlogPosts(page: Int): List<BlogPost> {
        return api.getPosts(page = page)
    }
}
