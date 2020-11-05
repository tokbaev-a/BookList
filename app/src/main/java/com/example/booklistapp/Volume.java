package com.example.booklistapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Volume {
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("authors")
    @Expose
    private List<String> mAuthors;
    @SerializedName("averageRating")
    @Expose
    private float mAverageRating;
    @SerializedName("infoLink")
    @Expose
    private String mInfoLink;
    @SerializedName("pageCount")
    @Expose
    private Integer mPageCount;
    @SerializedName("publishedDate")
    @Expose
    private String mPublishedDate;
    @SerializedName("categories")
    @Expose
    private List<String> mCategory;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;

    public class ImageLinks {
        @SerializedName("thumbnail")
        @Expose
        private String mThumbnail;
        public String getThumbnail() {
            return mThumbnail;
        }
    }

    public Volume(String title, List<String> author, int pages, String date, List<String> category,
                  float rating, ImageLinks image, String url) {
        this.mTitle = title;
        this.mAuthors = author;
        this.mPageCount = pages;
        this.mCategory = category;
        this.mAverageRating = rating;
        this.imageLinks = image;
        this.mInfoLink = url;
        this.mPublishedDate = date;
    }

    public String getTitle() {
        return mTitle;
    }
    public List<String> getAuthors() {
        return mAuthors;
    }
    public ImageLinks getImageLinks() {
        return imageLinks;
    }
    public float getAverageRating() { return mAverageRating; }
    public String getPublishedDate(){ return mPublishedDate; }
    public String getInfoLink() {
        return mInfoLink;
    }
    public Integer getPageCount() { return mPageCount; }
    public List<String> getCategory() { return mCategory; }
}
