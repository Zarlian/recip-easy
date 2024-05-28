package com.example.recipeasy.data.deserializer
import com.example.recipeasy.data.dataclasses.Ingredient
import com.example.recipeasy.data.dataclasses.MealDetails
import com.google.gson.*
import java.lang.reflect.Type
class MealDetailsDeserializer : JsonDeserializer<MealDetails> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MealDetails {
        val jsonObject = json?.asJsonObject
        val ingredients = mutableListOf<Ingredient>()

        // Loop through strIngredient and strMeasure fields
        for (i in 1..20) {
            val ingredientName = jsonObject?.get("strIngredient$i").toString().replace("\"", "")
            val measure = jsonObject?.get("strMeasure$i").toString().replace("\"", "")


            if (measure != "null" && measure.isNotBlank()) {
                if (ingredientName != "null" && ingredientName.isNotBlank()) {
                    ingredients.add(Ingredient(ingredientName, measure))
                }
            }
        }

        return MealDetails(
            idMeal = jsonObject?.get("idMeal")?.asString.orEmpty(),
            strMeal = jsonObject?.get("strMeal")?.asString.orEmpty(),
            strInstructions = jsonObject?.get("strInstructions")?.asString.orEmpty(),
            strMealThumb = jsonObject?.get("strMealThumb")?.asString.orEmpty(),
            ingredients = ingredients,

            // Add default values for difficulty, servings and time
            difficulty = "Easy" ,
            servings = "4" ,
            time =  "30"
        )
    }
}
