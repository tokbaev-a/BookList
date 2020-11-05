package com.example.booklistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText searchText = findViewById(R.id.text_field);
        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = searchText.getText().toString();
                if(text.isEmpty() || text.equals(" ")){
                    Toast.makeText(SearchActivity.this, "Empty field\nPlease enter the book name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(SearchActivity.this, BookActivity.class);
                    intent.putExtra("Search", text);
                    startActivity(intent);
                }
            }
        });
    }

}
