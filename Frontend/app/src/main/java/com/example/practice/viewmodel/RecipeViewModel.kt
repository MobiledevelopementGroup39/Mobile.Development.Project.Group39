package com.example.practice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.example.practice.api.allRecipeData


class RecipeViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: LiveData<Recipe?> get() = _recipe

    fun fetchAllRecipes() {
        _recipes.value = allRecipeData
    }

    fun fetchRecipe(documentId: String) {
        _recipe.value = allRecipeData.find { it.id.toString() == documentId }
    }
}
//    fun fetchAllRecipes() {
//
//        firestore.collection("recipeTutorial")
//            .get()
//            .addOnSuccessListener { result ->
//                val recipeList = result.mapNotNull {doc ->
//                    doc.toObject<Recipe>()?.copy(id = doc.id) }
//                _recipes.value = recipeList
//            }
//            .addOnFailureListener { exception ->
//                Log.e("FirestoreError", "Error fetching recipes", exception)
//            }
//    }
//
//    fun fetchRecipe(documentId: String) {
//        firestore.collection("recipeTutorial")
//            .document(documentId)
//            .get()
//            .addOnSuccessListener { document ->
//                _recipe.value = document.toObject(Recipe::class.java)
//            }
//            .addOnFailureListener { exception ->
//                Log.e("FirestoreError", "Error fetching recipe", exception)
//            }
//    }
//}




//package com.example.practice.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
//
//class RecipeViewModel : ViewModel() {
//
//    private val firestore = FirebaseFirestore.getInstance()
//
//    private val _recipes = MutableLiveData<List<Recipe>>()
//    val recipes: LiveData<List<Recipe>> get() = _recipes
//
//    private val _recipe = MutableLiveData<Recipe?>()
//    val recipe: LiveData<Recipe?> get() = _recipe
//
//    fun fetchAllRecipes() {
//        firestore.collection("recipeTutorial")
//            .get()
//            .addOnSuccessListener { result ->
//                val recipeList = result.mapNotNull { doc ->
//                    doc.toObject<Recipe>()?.copy(id = doc.id)}
//                _recipes.value = recipeList
//            }
//            .addOnFailureListener { exception ->
//                Log.e("FirestoreError", "Error fetching recipes", exception)
//            }
//    }
//
//    fun fetchRecipe(documentId: String) {
//        firestore.collection("recipeTutorial")
//            .document(documentId)
//            .get()
//            .addOnSuccessListener { document ->
//                _recipe.value = document.toObject(Recipe::class.java)
//            }
//            .addOnFailureListener { exception ->
//                Log.e("FirestoreError", "Error fetching recipe", exception)
//            }
//    }
//}
//
////package com.example.practice.viewmodel
////
////import android.util.Log
////import androidx.lifecycle.LiveData
////import androidx.lifecycle.MutableLiveData
////import androidx.lifecycle.ViewModel
////import com.google.firebase.firestore.FirebaseFirestore
////class RecipeViewModel : ViewModel() {
////    private val _allRecipes = MutableLiveData<List<Recipe>>()
////    val allRecipes: LiveData<List<Recipe>> get() = _allRecipes
////
////    fun fetchAllRecipes() {
////        firestore.collection("recipeTutorial")
////            .get()
////            .addOnSuccessListener { result ->
////                val recipes = result.mapNotNull { it.toObject(Recipe::class.java) }
////                _allRecipes.value = recipes
////            }
////            .addOnFailureListener { exception ->
////                Log.e("FirestoreError", "Error fetching recipes", exception)
////            }
////    }
////
////
//////    private val _recipe = MutableLiveData<Recipe?>()
//////    val recipe: LiveData<Recipe?> get() = _recipe
//////
//////    private val firestore = FirebaseFirestore.getInstance()
//////
//////    fun fetchRecipe(documentId: String) {
//////        firestore.collection("recipeTutorial")
//////            .document(documentId)
//////            .get()
//////            .addOnSuccessListener { document ->
//////                _recipe.value = document.toObject(Recipe::class.java)
//////            }
//////            .addOnFailureListener { exception ->
//////                Log.e("FirestoreError", "Error fetching recipe", exception)
//////            }
//////    }
////}