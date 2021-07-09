package com.example.recipeezapp.view.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recipeezapp.R;
import com.example.recipeezapp.adapter.MealByCategoryAdapter;
import com.example.recipeezapp.adapter.OnItemClickListener;
import com.example.recipeezapp.model.Categories;
import com.example.recipeezapp.model.Meals;
import com.example.recipeezapp.view.home.HomeActivity;
import com.example.recipeezapp.view.details.DetailsActivity;

import java.util.List;

import static com.example.recipeezapp.view.home.HomeActivity.EXTRA_DETAIL;

public class CategoryActivity extends AppCompatActivity implements CategoryView, OnItemClickListener {

    ImageView imageCategoryBg;
    ImageView imageCategory;
    TextView textCategory;

    RecyclerView mealByCategoryRv;

    CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        imageCategoryBg = findViewById(R.id.imageCategoryBg);
        imageCategory = findViewById(R.id.imageCategory);
        textCategory = findViewById(R.id.textCategory);

        presenter = new CategoryPresenter(this);
        mealByCategoryRv = findViewById(R.id.mealByCategory_rv);

        initIntent();


    }


    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories =
                (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);

        presenter.getMealByCategory(categories.get(position).getStrCategory());

        textCategory.setText(categories.get(position).getStrCategoryDescription());

        Glide
                .with(this)
                .load(categories.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.sample_image_category)
                .into(imageCategoryBg);
        Glide
                .with(this)
                .load(categories.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.sample_image_category)
                .into(imageCategory);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        MealByCategoryAdapter adapter = new MealByCategoryAdapter(meals, this,this);
        mealByCategoryRv.setLayoutManager(new GridLayoutManager(this, 2));
        mealByCategoryRv.setClipToPadding(false);
        mealByCategoryRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onMealClick(View view, int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        TextView mealName = view.findViewById(R.id.mealName);
        intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
        startActivity(intent);
    }
}