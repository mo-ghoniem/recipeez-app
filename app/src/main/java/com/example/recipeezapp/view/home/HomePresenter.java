package com.example.recipeezapp.view.home;

import com.example.recipeezapp.Utils;
import com.example.recipeezapp.model.Categories;
import com.example.recipeezapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    void getMeals() {
        Call<Meals> mealsCall = Utils.getApi().getMealsByFirstLetter("b");
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful() && response.body() != null) {
                    homeView.setMeal(response.body().getMeals());
                }else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    void getCategories() {
        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.isSuccessful() && response.body() != null) {
                    homeView.setCategory(response.body().getCategories());
                }else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
