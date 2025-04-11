package com.example.foodlearningapp.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val selectedMediaUri = mutableStateOf<Uri?>(null)
    val isPosting = mutableStateOf(false)
    val postSuccess = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    fun updateTitle(newTitle: String) {
        title.value = newTitle
    }

    fun updateDescription(newDescription: String) {
        description.value = newDescription
    }

    fun setSelectedMediaUri(uri: Uri?) {
        selectedMediaUri.value = uri
    }

    fun clearPostResult() {
        postSuccess.value = null
        errorMessage.value = null
    }

    fun submitPost() {
        if (title.value.isBlank() || selectedMediaUri.value == null) {
            errorMessage.value = "Please add a title and select media."
            return
        }

        isPosting.value = true
        errorMessage.value = null // Clear any previous error

        // Simulate a network request (replace with your actual API call)
        viewModelScope.launch {
            delay(2000) // Simulate network delay
            val success = true // Replace with actual API result based on your backend

            if (success) {
                postSuccess.value = true
                // Optionally clear the input fields after successful post
                title.value = ""
                description.value = ""
                selectedMediaUri.value = null
            } else {
                errorMessage.value = "Failed to upload post."
            }
            isPosting.value = false
        }
    }
}

