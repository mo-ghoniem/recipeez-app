package com.example.recipeezapp.view.home;

import com.example.recipeezapp.model.Categories;
import com.example.recipeezapp.model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
