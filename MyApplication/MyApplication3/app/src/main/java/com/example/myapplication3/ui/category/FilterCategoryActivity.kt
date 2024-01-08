package com.example.myapplication3.ui.category

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

class FilterCategoryActivity : AppCompatActivity() {
    private var _binding: FiltreCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv: RecyclerView
    private lateinit var filtrecategoryadapter: FilterCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FiltreCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv = findViewById(R.id.rvcategory)

        filtrecategoryadapter = FilterCategoryAdapter(this, emptyArray(), emptyArray(), emptyArray())
        rv.adapter = filtrecategoryadapter
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


        api.getFilterCategories(category).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val images = response.body()?.drinks?.mapNotNull { it.strDrinkThumb }
                    val names = response.body()?.drinks?.mapNotNull { it.strDrink }
                    val ids = response.body()?.drinks?.mapNotNull { it.idDrink } // Extrayez les IDs

                    images?.let {
                        filtrecategoryadapter.setimagesData(it.toTypedArray())
                    }
                    names?.let {
                        filtrecategoryadapter.setnamesData(it.toTypedArray())
                    }

                    ids?.let {
                        filtrecategoryadapter.setIdsData(it.toTypedArray())
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
