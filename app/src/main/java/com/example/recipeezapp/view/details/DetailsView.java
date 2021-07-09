package com.example.recipeezapp.view.details;

import com.example.recipeezapp.model.Meals;

public interface DetailsView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
