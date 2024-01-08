package com.example.myapplication3.ui.ingredients

import ApiService
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R
import com.example.myapplication3.databinding.FiltreCategoryBinding
import com.example.myapplication3.ui.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilterIngredientsActivity : AppCompatActivity() {
    private var _binding: FiltreCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv: RecyclerView
    private lateinit var filtre: FilterIngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FiltreCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv = findViewById(R.id.rvcategory)

        filtre = FilterIngredientsAdapter(this, emptyArray(), emptyArray(), emptyArray())
        rv.adapter = filtre
        rv.layoutManager = LinearLayoutManager(this)

        val category = intent.getStringExtra("category")
        if (category != null) {
            fetchDataFromApi(category)
        }
    }

    private fun fetchDataFromApi(category : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)


        api.getFilteringredients(category).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val images = response.body()?.drinks?.mapNotNull { it.strDrinkThumb }
                    val names = response.body()?.drinks?.mapNotNull { it.strDrink }
                    val ids = response.body()?.drinks?.mapNotNull { it.idDrink }

                    images?.let {
                        filtre.setimagesData(it.toTypedArray())
                    }
                    names?.let {
                        filtre.setnamesData(it.toTypedArray())
                    }

                    ids?.let {
                        filtre.setIdsData(it.toTypedArray())
                    }

                } else {
                    Log.e("API_ERROR", "Échec de la récupération des données: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("API_FAILURE", "La requête API a échoué: ${t.message}")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
