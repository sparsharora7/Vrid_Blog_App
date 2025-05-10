package com.example.vridblogapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vridblogapp.model.BlogPost
import com.example.vridblogapp.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    private val repository = BlogRepository()

    private val _blogs = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogs: StateFlow<List<BlogPost>> = _blogs

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchBlogs()
    }

    fun fetchBlogs(page: Int = 1) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val posts = repository.getBlogPosts(page)
                _blogs.value = posts
            } catch (e: Exception) {
                _error.value = "Failed to load blog posts."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
