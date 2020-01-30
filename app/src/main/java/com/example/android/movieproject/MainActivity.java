package com.example.android.movieproject;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.movieproject.API.Client;
import com.example.android.movieproject.API.Service;
import com.example.android.movieproject.Adapter.FilmeAdapter;
import com.example.android.movieproject.Adapter.FilmeDbAdapter;
import com.example.android.movieproject.Data.MoviesContract;
import com.example.android.movieproject.Data.MoviesDbHelper;
import com.example.android.movieproject.Model.Filme;
import com.example.android.movieproject.Model.FilmeResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{


    private RecyclerView myrv;
    private static String movieListState = "movieListState";
    private static final String SAVE_MOVIE_STATE = "movieState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Filme> lstFilme = new ArrayList<>();
        myrv = findViewById(R.id.recyclerView_id);

        if(savedInstanceState != null){

            Toast.makeText(getBaseContext(), "savedInstanceState!= null", Toast.LENGTH_SHORT).show();
            String savedMovieState = savedInstanceState.getString(SAVE_MOVIE_STATE);

            if(savedMovieState.equals("popState")){

                showPopularMovies();
                Toast.makeText(getBaseContext(), "savedMoviesState.equals(popState). movieListState:" + movieListState, Toast.LENGTH_LONG).show();

            } else if(savedMovieState.equals("highState")){

                showHighestRatedMovies();
                Toast.makeText(getBaseContext(), "savedMoviesState.equals(highState). movieListState:  " + movieListState, Toast.LENGTH_LONG).show();

            } else if(savedMovieState.equals("favoState")){

                Toast.makeText(getBaseContext(),"savedMoviesState.equals(favoState). movieListState:  " + movieListState, Toast.LENGTH_LONG).show();
                showFavoriteMovies();

            }

        }

        //set up recyclerview and show popularmovies
        com.example.android.movieproject.Adapter.FilmeAdapter filmeAdapter = new FilmeAdapter(this, lstFilme);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(filmeAdapter);

        showPopularMovies();
        movieListState = "pop";

        // dbHelper stuff.
        MoviesDbHelper moviesDbHelper = new MoviesDbHelper(this);
        SQLiteDatabase mDb = moviesDbHelper.getReadableDatabase();

        //declare toolbar and set it.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_themoviedblogo);

        final TextView tvToolTitle = findViewById(R.id.tvToolbarTitle);
        tvToolTitle.setText("   Popular Movies");

        //toolbar section: switches between movie section's (popular, rate and favorites)
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getItemId()){

                    case R.id.pop:
                        showPopularMovies();
                        tvToolTitle.setText("   Popular Movies");
                        movieListState = "popState";
                        break;

                    case R.id.high:
                        showHighestRatedMovies();
                        tvToolTitle.setText("   Highest Rated Movies");
                        movieListState = "highState";
                        Toast.makeText(getBaseContext(), movieListState + ": saved", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.favo:
                        showFavoriteMovies();
                        tvToolTitle.setText("   Favorite Movies");
                        movieListState = "favoState";
                        break;
                }

                return false;
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String savingMovieState = movieListState;
        outState.putString(SAVE_MOVIE_STATE, savingMovieState);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meumenu, menu);
        return true;
    }

    //disable menuItem's when selected, how it works: if you select favorite movies you will disable it and enable the others. Same logic for the others.
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        final MenuItem pop = menu.findItem(R.id.pop);
        final MenuItem high = menu.findItem(R.id.high);
        final MenuItem favo = menu.findItem(R.id.favo);

        //start with popular movies disabled because the app launches showing it.
        if(pop.isEnabled() && high.isEnabled() && favo.isEnabled()) pop.setEnabled(false);

        high.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                pop.setEnabled(true);
                high.setEnabled(false);
                favo.setEnabled(true);
                return false;
            }
        });

        pop.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                pop.setEnabled(false);
                high.setEnabled(true);
                favo.setEnabled(true);
                return false;
            }
        });

        favo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                favo.setEnabled(false);
                high.setEnabled(true);
                pop.setEnabled(true);
                return false;
            }
        });

        return true;
    }

    // display popular movies
    @SuppressWarnings("unused")
    private void showPopularMovies(){
        try{

            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);

            Call<FilmeResponse> call = apiService.getPopularMovies(BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<FilmeResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onResponse(@NonNull Call<FilmeResponse> call, @NonNull Response<FilmeResponse> response) {
                    List<Filme> filmes = response.body() != null ? Objects.requireNonNull(response.body()).getResults() : null;
                    myrv.setAdapter(new FilmeAdapter(getApplicationContext(), filmes));
                }

                @Override
                public void onFailure(@NonNull Call<FilmeResponse> call, @NonNull Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // display highest rated movies
    @SuppressWarnings("unused")
    private void showHighestRatedMovies(){
        Toast.makeText(MainActivity.this, "INSIDE TOP RATED!", Toast.LENGTH_SHORT).show();
        try{

            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<FilmeResponse> call = apiService.getTopRatedMovies(BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<FilmeResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onResponse(@NonNull Call<FilmeResponse> call, @NonNull Response<FilmeResponse> response) {
                    List<Filme> filmes = response.body() != null ? Objects.requireNonNull(response.body()).getResults() : null;
                    myrv.setAdapter(new FilmeAdapter(getApplicationContext(), filmes));
                }

                @Override
                public void onFailure(@NonNull Call<FilmeResponse> call, @NonNull Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // query data via content resolver and display favorite movies.
    private void showFavoriteMovies(){

        Cursor cursor = getData();
        FilmeDbAdapter dbAdapter = new FilmeDbAdapter(this, cursor);
        myrv.setAdapter(dbAdapter);


    }

    private Cursor getData(){

        return getContentResolver().query(MoviesContract.MoviesEntry.CONTENT_URI, null, null, null , null);

    }



}






