package com.example.recipeasy.network

import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.data.deserializer.MealDetailsDeserializer
import com.example.recipeasy.data.dataclasses.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"


private val gson = GsonBuilder()
    .registerTypeAdapter(MealDetails::class.java, MealDetailsDeserializer())
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface APIservice
{
    @GET("random.php")
    suspend fun getRandomRecipe(): Recipe

    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") recipeId: String): Recipe

    @GET("filter.php")
    suspend fun getRecipesByIngredient(@Query("i") ingredient: String): Recipe

}

object recipeApi {

    val retrofitService: APIservice by lazy {
        retrofit.create(APIservice::class.java)
    }
}
