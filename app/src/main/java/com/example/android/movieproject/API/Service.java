package com.example.android.movieproject.API;

import com.example.android.movieproject.Model.FilmeResponse;
import com.example.android.movieproject.Model.ReviewResponse;
import com.example.android.movieproject.Model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    String baseURL = "http://api.themoviedb.org/3/";

    @GET("movie/popular")
    Call<FilmeResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<FilmeResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("movie_id") int id, @Query("api_key") String apiKey);



}
