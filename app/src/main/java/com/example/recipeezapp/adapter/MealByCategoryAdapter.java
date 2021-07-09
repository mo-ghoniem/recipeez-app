package com.example.recipeezapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeezapp.R;
import com.example.recipeezapp.model.Meals;

import java.util.List;

public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.MealByCategoryViewHolder> {
    private List<Meals.Meal> mealList;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public MealByCategoryAdapter(List<Meals.Meal> mealList, Context context, OnItemClickListener onItemClickListener) {
        this.mealList = mealList;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MealByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealByCategoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item, parent, false), mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByCategoryViewHolder holder, int position) {
        holder.mealText.setText(mealList.get(position).getStrMeal());

        Glide
                .with(context)
                .load(mealList
                        .get(position)
                        .getStrMealThumb())
                .placeholder(R.drawable.sample_image_meal)
                .into(holder.mealImage);

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MealByCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mealText;
        ImageView mealImage;

        OnItemClickListener onItemClickListener;

        public MealByCategoryViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            mealText = itemView.findViewById(R.id.mealName);
            mealImage = itemView.findViewById(R.id.mealImage);

            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onMealClick(v, getAdapterPosition());
        }
    }
}
