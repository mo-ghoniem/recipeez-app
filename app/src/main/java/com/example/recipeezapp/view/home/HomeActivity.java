package com.example.recipeezapp.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.recipeezapp.R;
import com.example.recipeezapp.adapter.CategoryAdapter;
import com.example.recipeezapp.adapter.FeaturedListAdapter;
import com.example.recipeezapp.adapter.OnItemClickListener;
import com.example.recipeezapp.databinding.ActivityMainBinding;
import com.example.recipeezapp.model.Categories;
import com.example.recipeezapp.model.Meals;
import com.example.recipeezapp.view.category.CategoryActivity;
import com.example.recipeezapp.view.details.DetailsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView, OnItemClickListener {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    public static final String EXTRA_MEAL = "meal";
    public static final String EXTRA_MEAL_POSITION = "position";

    private static final String TAG = "HomeActivity";

    private List<Categories.Category> listCategory = new ArrayList<>();
    private List<Meals.Meal> listMeal = new ArrayList<>();

    private ActivityMainBinding binding;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



         presenter = new HomePresenter(this);
         presenter.getMeals();
         presenter.getCategories();


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        FeaturedListAdapter adapter = new FeaturedListAdapter(this, meal, this);
        binding.popularRv.setAdapter(adapter);
        binding.popularRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        listMeal.addAll(meal);
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        CategoryAdapter adapter = new CategoryAdapter(this, category, this);
        binding.categoryRv.setAdapter(adapter);
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(this));

        listCategory.addAll(category);

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Serializable) listCategory);
        intent.putExtra(EXTRA_POSITION, position);
        startActivity(intent);

    }

    @Override
    public void onMealClick(View view, int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        TextView mealName = view.findViewById(R.id.mealName);
        intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
        startActivity(intent);
    }

}
