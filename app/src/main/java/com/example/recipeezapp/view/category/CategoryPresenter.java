package com.example.recipeezapp.view.category;

import androidx.annotation.NonNull;

import com.example.recipeezapp.Utils;
import com.example.recipeezapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private CategoryView categoryView;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    void getMealByCategory(String category) {
        Call<Meals> mealsCall = Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if(response.isSuccessful() && response.body() != null) {
                    categoryView.setMeals(response.body().getMeals());
                }else {
                    categoryView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                categoryView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
