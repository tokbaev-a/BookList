package com.example.booklistapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("books/v1/volumes?")
    Call<Book> getBooks(@Query("q") String input);
}
