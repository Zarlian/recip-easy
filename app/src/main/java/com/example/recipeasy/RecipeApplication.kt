package com.example.recipeasy

import android.app.Application
import com.example.recipeasy.data.AppContainer
import com.example.recipeasy.data.AppDataContainer

class RecipeApplication : Application(){

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}