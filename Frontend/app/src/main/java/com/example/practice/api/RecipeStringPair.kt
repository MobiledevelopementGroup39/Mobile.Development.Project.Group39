package com.example.practice.api

import androidx.annotation.DrawableRes
import com.example.practice.R

data class RecipeStringPair(
    @DrawableRes val drawable: Int,
    val recipeTitle: String,
    val recipeDetails: String,
    val gao: String,
    val reactionCount: String
)


val allRecipeData = listOf(
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k"),
    Triple(
        R.drawable.pizza,
        "R.string.Caffee_Mocha",
        "R.string.Deep_Foam"
    ) to Pair("Goa", "27.7k")
).map {
    RecipeStringPair(
        drawable = it.first.first,
        recipeTitle = it.first.second,
        recipeDetails = it.first.third,
        gao = it.second.first,
        reactionCount = it.second.second
    )
}
