package com.example.android.movieproject;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.movieproject.API.Service;
import com.example.android.movieproject.API.Cliente;
import com.example.android.movieproject.Adapter.ReviewAdapter;
import com.example.android.movieproject.Adapter.TrailerAdapter;
import com.example.android.movieproject.Data.MoviesContract;
import com.example.android.movieproject.Model.Review;
import com.example.android.movieproject.Model.ReviewResponse;
import com.example.android.movieproject.Model.Trailer;
import com.example.android.movieproject.Model.TrailerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Essa classe recebe os dados pela intent criada no clicklistener encontrado no FilmeAdapter e mostra na tela

public class DetalhesFilme extends AppCompatActivity {

    private RecyclerView recyclerViewReview;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;

    private RecyclerView recyclerViewTrailer;
    private TrailerAdapter trailerAdapter;
    private List<Trailer> trailerList;

    public int movie_id;
    public static String nomeFilme;
    public String posterURL;
    public String releaseDate;
    public Double rating;
    public String ParseRating;
    public String plot;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes);

        //recebendo os dados

        Intent intent = getIntent();

        nomeFilme = Objects.requireNonNull(intent.getExtras()).getString("nomeFilme");
        rating = intent.getExtras().getDouble("rating");
        ParseRating = Double.toString(rating);
        posterURL = intent.getExtras().getString("thumbnail");
        releaseDate = intent.getExtras().getString("releaseDate");
        plot = intent.getExtras().getString("plot");
        movie_id = intent.getExtras().getInt("idFilme");

        TextView plotFilme = findViewById(R.id.tv_plot);
        TextView dataFilme = findViewById(R.id.tv_date);
        TextView tituloFilme = findViewById(R.id.tv_nome_filme_id);
        TextView ratingFilme = findViewById(R.id.tv_rating);
        ImageView posterFilme = findViewById(R.id.filmeThumbnail);
        Button botaoFavoritoFilme = findViewById(R.id.btn_add_favorite);

        //mostrando os dados recebidos

        Glide.with(this).load(posterURL).into(posterFilme);
        plotFilme.setText(plot);
        dataFilme.setText("Release: " + releaseDate);
        ratingFilme.setText("Rating: " + ParseRating);
        tituloFilme.setText(nomeFilme);


        startTrailer();
        startReviews();

        botaoFavoritoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "testing insert button click", Toast.LENGTH_SHORT).show();

                //adiciona favoritos
                addToFavorite();
            }
        });

    }

    private void startReviews() {

        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, reviewList);

        recyclerViewReview = (RecyclerView) findViewById(R.id.recyclerView_review_id);
        RecyclerView.LayoutManager managerReview = new LinearLayoutManager(this);
        recyclerViewReview.setLayoutManager(managerReview);
        recyclerViewReview.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();

        try{
            if (BuildConfig.DB_API_KEY.isEmpty()){
                Toast.makeText(getApplicationContext(), "You need an API Key! Go get one :)", Toast.LENGTH_SHORT).show();
                return;
            }
            Cliente Cliente = new Cliente();
            Service apiService = Cliente.getClient().create(Service.class);
            Call<ReviewResponse> call = apiService.getReviews(movie_id, BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<ReviewResponse>() {
                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {

                    List<Review> review = response.body().getResults();
                    recyclerViewReview.setAdapter(new ReviewAdapter(getApplicationContext(), review));

                }

                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetalhesFilme.this, "Error fetching the data" + movie_id, Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    private void startTrailer() {

        trailerList = new ArrayList<>();
        trailerAdapter = new TrailerAdapter(this, trailerList);

        recyclerViewTrailer = (RecyclerView) findViewById(R.id.recyclerView_trailer_id);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewTrailer.setLayoutManager(manager);
        recyclerViewTrailer.setAdapter(trailerAdapter);
        trailerAdapter.notifyDataSetChanged();

        try{
            if (BuildConfig.DB_API_KEY.isEmpty()){
                Toast.makeText(getApplicationContext(), "You need an API Key! Go get one :)", Toast.LENGTH_SHORT).show();
                return;
            }
            Cliente Cliente = new Cliente();
            Service apiService = Cliente.getClient().create(Service.class);
            Call<TrailerResponse> call = apiService.getMovieTrailer(movie_id, BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {

                    List<Trailer> trailer = response.body().getResults();
                    recyclerViewTrailer.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));

                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetalhesFilme.this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addToFavorite(){

        ContentValues contentValues = new ContentValues();

        contentValues.put(MoviesContract.MoviesEntry.COLUMN_MOVIE_ID, movie_id);
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_TITLE, nomeFilme);
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH, posterURL);
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_RATING, ParseRating);
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE, releaseDate);
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_SYNOPSIS, plot);


        Uri uri = getContentResolver().insert(MoviesContract.MoviesEntry.CONTENT_URI, contentValues);

        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

         // Finish activity (this returns back to MainActivity)
        //finish();


    }





}