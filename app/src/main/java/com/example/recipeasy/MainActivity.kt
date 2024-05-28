package com.example.recipeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.recipeasy.ui.RecipEasyApp
import com.example.recipeasy.ui.theme.RecipeasyTheme
import com.example.recipeasy.worker.DailyRecipeWorker

import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val workRequest = PeriodicWorkRequestBuilder<DailyRecipeWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(this).enqueue(workRequest)


        super.onCreate(savedInstanceState)
        setContent {
            RecipeasyTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        RecipEasyApp()
                    }
            }
        }
    }
}


