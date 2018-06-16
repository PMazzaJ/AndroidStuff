package com.example.android.movieproject.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmeResponse {

    @SerializedName("results")
    private List<Filme> results;
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
                return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Filme> getResults() {
        return results;
    }


    public List<Filme> getMovies() {
        return results;
    }

    public void setResults(List<Filme> results) {

        this.results = results;
    }

    public void setMovies(List<Filme> results) {
        this.results = results;
    }

    public FilmeResponse() {
    }

    public FilmeResponse(List<Filme> results, int page, int totalResults, int totalPages) {
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }
}
