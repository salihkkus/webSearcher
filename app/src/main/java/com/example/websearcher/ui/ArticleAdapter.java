package com.example.websearcher.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.websearcher.R;
import com.example.websearcher.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final List<Article> articles;
    private final OnArticleClickListener onArticleClickListener;

    // Constructor with onArticleClickListener
    public ArticleAdapter(List<Article> articles, OnArticleClickListener onArticleClickListener) {
        this.articles = articles;
        this.onArticleClickListener = onArticleClickListener;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView readingTimeTextView;
        public ImageView articleImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            readingTimeTextView = itemView.findViewById(R.id.textViewReadingTime);
            articleImageView = itemView.findViewById(R.id.imageViewIcon);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);

        // Title and reading time
        holder.titleTextView.setText(article.getTitle());
        String readingTimeText = holder.itemView.getContext()
                .getString(R.string.reading_time_format, article.getReadingTime());
        holder.readingTimeTextView.setText(readingTimeText);

        // Load icon using Glide
        String iconUrl = article.getIconUrl();
        if (iconUrl != null && !iconUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(iconUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(holder.articleImageView);
        } else {
            holder.articleImageView.setImageResource(R.drawable.placeholder_image);
        }

        // Click listener for the item
        holder.itemView.setOnClickListener(v -> {
            if (onArticleClickListener != null) {
                onArticleClickListener.onArticleClick(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // OnArticleClickListener interface
    public interface OnArticleClickListener {
        void onArticleClick(Article article);
    }
}
