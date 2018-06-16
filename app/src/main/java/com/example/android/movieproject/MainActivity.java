package com.example.android.movieproject;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.android.movieproject.API.Cliente;
import com.example.android.movieproject.API.Service;
import com.example.android.movieproject.Adapter.FilmeAdapter;
import com.example.android.movieproject.Model.Filme;
import com.example.android.movieproject.Model.FilmeResponse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Insert your API key -> gradle.properties DB_API_Key ="api_key"


public class MainActivity extends AppCompatActivity{

    private List<Filme> lstFilme;
    private RecyclerView myrv;
    private FilmeAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Filme> lstFilme = new ArrayList<>();
        myrv = findViewById(R.id.recyclerView_id);

        myAdapter = new FilmeAdapter(this, lstFilme);

        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);



        ChamaFilmesPopulares();




    }

    @SuppressWarnings("unused")
    private void ChamaFilmesPopulares(){
        try{

            Cliente cliente = new Cliente();
            Service apiService = Cliente.getClient().create(Service.class);

            Call<FilmeResponse> call = apiService.getPopularMovies(BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<FilmeResponse>() {
                @Override
                public void onResponse(@NonNull Call<FilmeResponse> call, @NonNull Response<FilmeResponse> response) {
                    List<Filme> filmes = response.body() != null ? response.body().getResults() : null;
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

    @SuppressWarnings("unused")
    private void ChamaFilmesTopRated(){
        try{

            Cliente cliente = new Cliente();
            Service apiService = Cliente.getClient().create(Service.class);
            Call<FilmeResponse> call = apiService.getTopRatedMovies(BuildConfig.DB_API_KEY);
            call.enqueue(new Callback<FilmeResponse>() {
                @Override
                public void onResponse(@NonNull Call<FilmeResponse> call, @NonNull Response<FilmeResponse> response) {
                    List<Filme> filmes = response.body() != null ? response.body().getResults() : null;
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.meumenu, menu);
        MenuItem popMenuItem = menu.findItem(R.id.pop);
        MenuItem highMenuItem = menu.findItem(R.id.high);
        MenuItem favMenuItem = menu.findItem(R.id.favo);

        popMenuItem.setEnabled(false);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        final MenuItem pop = menu.findItem(R.id.pop);
        final MenuItem high = menu.findItem(R.id.high);

        high.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                pop.setEnabled(true);
                high.setEnabled(false);
                return false;
            }
        });

        pop.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                pop.setEnabled(false);
                high.setEnabled(true);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

         switch (item.getItemId()){
             case R.id.pop:
                ChamaFilmesPopulares();
                break;

            case R.id.high:
                ChamaFilmesTopRated();
                break;

             case R.id.favo:

                 break;


         }


        return super.onOptionsItemSelected(item);
    }



}






