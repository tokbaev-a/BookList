package com.example.booklistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookActivity extends AppCompatActivity {

    private List<Items> booksList = new ArrayList<>();
    BooksAdapter adapter;
    RecyclerView recyclerView;
    BooksAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.loading);
        setClickListener();
        isOnline();
    }

    private void getBooksResponse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String searchWord = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).getString("Search")).replace(" ", "+");
        Interface requestInterface = retrofit.create(Interface.class);
        Call<Book> call = requestInterface.getBooks(searchWord);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                assert response.body() != null;
                booksList = response.body().getItems();
                progressBar.setVisibility(View.GONE);
                adapter = new BooksAdapter(BookActivity.this, booksList, listener);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setClickListener() {
        listener = new BooksAdapter.RecyclerViewClickListener() {
            @Override
            public void OnClick(View v, int position) {
                Intent bookIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(booksList.get(position).getVolumeInfo().getInfoLink()));
                startActivity(bookIntent);
            }
        };
    }

    public void isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            getBooksResponse();
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }
}