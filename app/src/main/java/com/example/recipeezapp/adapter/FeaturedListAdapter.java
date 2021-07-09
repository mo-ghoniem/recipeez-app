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

public class FeaturedListAdapter extends RecyclerView.Adapter<FeaturedListAdapter.FeaturedListViewHolder> {
    private Context context;
    private List<Meals.Meal> featuredMeals;
    private OnItemClickListener mOnItemClickListener;

    public FeaturedListAdapter(Context context, List<Meals.Meal> featuredMeals, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.featuredMeals = featuredMeals;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FeaturedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeaturedListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false),
                mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedListViewHolder holder, int position) {
        Glide
                .with(context)
                .load(featuredMeals
                        .get(position)
                        .getStrMealThumb())
                .placeholder(R.drawable.sample_image_meal)
                .into(holder.mealImage);

        holder.mealText.setText(featuredMeals.get(position).getStrMeal());
    }

    @Override
    public int getItemCount() {
        return featuredMeals.size();
    }

    public class FeaturedListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mealText;
        ImageView mealImage;

        OnItemClickListener onItemClickListener;

        public FeaturedListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
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
