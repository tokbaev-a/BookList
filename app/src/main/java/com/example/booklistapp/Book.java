package com.example.booklistapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Book {

    @SerializedName("items")
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }
}
