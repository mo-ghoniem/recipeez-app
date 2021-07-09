package com.example.recipeezapp.api;

import com.example.recipeezapp.model.Categories;
import com.example.recipeezapp.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("search.php")
    Call<Meals> getMealsByFirstLetter(@Query("f") String name);

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

    @GET("search.php")
    Call<Meals> getMealsByName(@Query("s") String mealName);
}
