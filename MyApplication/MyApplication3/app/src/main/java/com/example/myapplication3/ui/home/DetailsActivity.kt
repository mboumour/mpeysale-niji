package com.example.myapplication3.ui.home

import ApiService
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication3.databinding.DetailsRecetteBinding
import com.example.myapplication3.ui.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailsRecetteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsRecetteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        val id = intent.getStringExtra("ID")
        if (id != null) {
            fetchDataFromApi(id)
        }
    }

    private fun fetchDataFromApi(id : String) {
        // Initialisation de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)


        api.getDetails(id).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val drinks = response.body()?.drinks
                    val drinkName = drinks?.get(0)?.strDrink
                    val strCategory = drinks?.get(0)?.strCategory
                    val strGlass = drinks?.get(0)?.strGlass
                    val strInstructions = drinks?.get(0)?.strInstructions
                    val strDrinkThumb = drinks?.get(0)?.strDrinkThumb


                    drinkName?.let {
                        binding.textView1.text = it
                    }
                    strCategory?.let {
                        binding.textView22.text = it
                    }
                    strGlass?.let {
                        binding.textView33.text = it
                    }
                    strInstructions?.let {
                        binding.textView44.text = it
                    }
                    strDrinkThumb?.let {
                        Glide.with(this@DetailsActivity)
                            .load(it)
                            .into(binding.imageView)
                    }

                    val ingredients = StringBuilder()
                    for (i in 1..15) {
                        val ingredient = when (i) {
                            1 -> drinks?.get(0)?.strIngredient1
                            2 -> drinks?.get(0)?.strIngredient2
                            3 -> drinks?.get(0)?.strIngredient3
                            4 -> drinks?.get(0)?.strIngredient4
                            5 -> drinks?.get(0)?.strIngredient5
                            6 -> drinks?.get(0)?.strIngredient6
                            7 -> drinks?.get(0)?.strIngredient7
                            8 -> drinks?.get(0)?.strIngredient8
                            9 -> drinks?.get(0)?.strIngredient9
                            10 -> drinks?.get(0)?.strIngredient10
                            11 -> drinks?.get(0)?.strIngredient11
                            12 -> drinks?.get(0)?.strIngredient12
                            13 -> drinks?.get(0)?.strIngredient13
                            14 -> drinks?.get(0)?.strIngredient14
                            15 -> drinks?.get(0)?.strIngredient15
                            else -> null
                        }

                        val measure = when (i) {
                            1 -> drinks?.get(0)?.strMeasure1
                            2 -> drinks?.get(0)?.strMeasure2
                            3 -> drinks?.get(0)?.strMeasure3
                            4 -> drinks?.get(0)?.strMeasure4
                            5 -> drinks?.get(0)?.strMeasure5
                            6 -> drinks?.get(0)?.strMeasure6
                            7 -> drinks?.get(0)?.strMeasure7
                            8 -> drinks?.get(0)?.strMeasure8
                            9 -> drinks?.get(0)?.strMeasure9
                            10 -> drinks?.get(0)?.strMeasure10
                            11 -> drinks?.get(0)?.strMeasure11
                            12 -> drinks?.get(0)?.strMeasure12
                            13 -> drinks?.get(0)?.strMeasure13
                            14 -> drinks?.get(0)?.strMeasure14
                            15 -> drinks?.get(0)?.strMeasure15
                            else -> null
                        }

                        if (!ingredient.isNullOrEmpty()) {
                            ingredients.append("- ").append(ingredient).append(" (").append(measure ?: "").append(")\n")
                        }
                    }

                    binding.textView55.text = ingredients.toString().trim()



                } else {
                    Log.e("API_ERROR", "Échec de la récupération des données: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("API_FAILURE", "La requête API a échoué: ${t.message}")
            }
        })

    }
}