import com.example.myapplication3.ui.CategoryResponse
import com.example.myapplication3.ui.IngredientsResponse
import com.example.myapplication3.ui.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list.php?i=list")
    fun getIngredients(): Call<IngredientsResponse>


    @GET("list.php?c=list")
    fun getCategories(): Call<CategoryResponse>

    //@GET("search.php?s=margarita")
    //fun getProduct(): Call<ProductResponse>
    @GET("search.php")
    fun getProduct(@Query("s") name: String): Call<ProductResponse>


    @GET("lookup.php")
    fun getDetails(@Query("i") id: String): Call<ProductResponse>

    @GET("filter.php")
    fun getFilterCategories(@Query("c") category: String): Call<ProductResponse>

    @GET("filter.php")
    fun getFilteringredients(@Query("i") category: String): Call<ProductResponse>

}
