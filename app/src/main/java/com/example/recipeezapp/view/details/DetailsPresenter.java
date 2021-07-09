package com.example.recipeezapp.view.details;

import androidx.annotation.NonNull;

import com.example.recipeezapp.Utils;
import com.example.recipeezapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {

    private DetailsView detailsView;

    public DetailsPresenter(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    void getMealByName(String name) {

        Utils.getApi().getMealsByName(name).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if(response.isSuccessful() && response.body() != null){
                    detailsView.setMeal(response.body().getMeals().get(0));
                }else {
                    detailsView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                detailsView.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}
