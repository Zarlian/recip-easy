package com.example.recipeasy.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.recipeasy.network.recipeApi

class DailyRecipeWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override suspend fun doWork(): Result {
            val context = applicationContext

            return try {
                val recipe = recipeApi.retrofitService.getRandomRecipe()
                val recipeName = recipe.meals[0].strMeal

                val notificationText = "Try: $recipeName"

                sendNotification("New recipe of the day: ", notificationText, context)

                Log.d("RecipeNotificationWorker", "Recipe: $recipeName")
                Result.success()
            } catch (e: Exception) {
                Log.e("WeatherNotificationWorker", "Error recipe of the day", e)
                Result.failure()
            }
        }
}