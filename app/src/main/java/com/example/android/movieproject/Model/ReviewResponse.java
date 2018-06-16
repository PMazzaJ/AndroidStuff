package com.example.android.movieproject.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("id")
    private int id_review;
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Review> results;

    public ReviewResponse() {
    }

    public ReviewResponse(int id_review, List<Review> results) {
        this.id_review = id_review;
        this.results = results;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getId_review() {
        return id_review;
    }

    public List<Review> getResults() {
        return results;
    }
}
