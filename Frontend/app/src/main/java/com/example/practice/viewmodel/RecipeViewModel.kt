package com.example.practice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
class RecipeViewModel : ViewModel() {

    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: LiveData<Recipe?> get() = _recipe

    private val firestore = FirebaseFirestore.getInstance()

    fun fetchRecipe(documentId: String) {
        firestore.collection("recipeTutorial")
            .document(documentId)
            .get()
            .addOnSuccessListener { document ->
                _recipe.value = document.toObject(Recipe::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error fetching recipe", exception)
            }
    }
}