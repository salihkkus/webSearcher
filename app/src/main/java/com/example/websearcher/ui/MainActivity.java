package com.example.websearcher.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.websearcher.R;
import com.example.websearcher.model.Article;
import com.example.websearcher.repository.ArticleRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddLinkBottomSheetFragment.OnUrlEnteredListener {

    private RecyclerView recyclerViewArticles;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;
    private FloatingActionButton fabAddLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_WebSearcher);
        setContentView(R.layout.activity_main);

        recyclerViewArticles = findViewById(R.id.recyclerViewArticles);
        fabAddLink = findViewById(R.id.fabAddLink);

        articleList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(articleList, article -> {
            // Open article in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            startActivity(intent);
        });

        recyclerViewArticles.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArticles.setAdapter(articleAdapter);

        fabAddLink.setOnClickListener(v -> {
            AddLinkBottomSheetFragment bottomSheet = new AddLinkBottomSheetFragment();
            bottomSheet.setOnUrlEnteredListener(this); // Set listener
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    @Override
    public void onUrlEntered(String url) {
        fetchArticle(url);
    }

    // Get article
    private void fetchArticle(String url) {
        new Thread(() -> {
            try {
                Article article = new ArticleRepository().fetchArticle(url);
                runOnUiThread(() -> addArticleToList(article));
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(this, getString(R.string.toast_error, e.getMessage()), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }

    private void addArticleToList(Article article) {
        articleList.add(0, article);
        articleAdapter.notifyItemInserted(0);
        recyclerViewArticles.scrollToPosition(0);
    }
}
