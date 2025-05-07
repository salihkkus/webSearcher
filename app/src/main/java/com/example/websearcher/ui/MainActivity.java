package com.example.websearcher.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.websearcher.R;
import com.example.websearcher.model.Article;
import com.example.websearcher.repository.ArticleRepository;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button buttonFetch;
    private TextView textViewTitle;
    private TextView textViewReadingTime;
    private ImageView imageViewArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        buttonFetch = findViewById(R.id.buttonFetch);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewReadingTime = findViewById(R.id.textViewReadingTime);
        imageViewArticle = findViewById(R.id.imageViewArticle);

        buttonFetch.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString().trim();
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ağ işlemini arka planda başlat
            new Thread(() -> {
                try {
                    Article article = new ArticleRepository().fetchArticle(url);
                    runOnUiThread(() -> displayArticle(article));
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() ->
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });
    }

    private void displayArticle(Article article) {
        textViewTitle.setText(article.getTitle());
        textViewReadingTime.setText(article.getReadingTime() + " dk");
        if (article.getImageUrl() != null) {
            Glide.with(this)
                    .load(article.getImageUrl())
                    .into(imageViewArticle);
        } else {
            imageViewArticle.setImageResource(R.drawable.placeholder_image);
        }
    }
}