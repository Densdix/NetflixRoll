package com.leknos.netflixroll;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String xmlRequest = "https://api.themoviedb.org/3/search/tv?api_key=862c044119db5df703d9b0d454af8251&language=en-US&query=";

    private HttpClient httpClient;
    private RecyclerView recyclerView;
    private MoviesListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private StringBuilder textResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Saved Movies");
        setSupportActionBar(toolbar);
        ///xmlData = findViewById(R.id.xml_text);
        httpClient = new HttpClient();
        recyclerView = findViewById(R.id.recycler_view);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final EditText input = new EditText(MainActivity.this);
                builder.setView(input);
                builder.setTitle("Search TV Show");

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new AsyncTaskParse().execute(input.getText().toString().trim());
                        postRequest(input.getText().toString().trim());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });


    }

    public void postRequest(String movieTitle) {
        if (!movieTitle.equals("")) {
            String title = movieTitle.replace(" ", "%20");
            Call<MoviePager> call = NetworkService.getInstance().getJSONApi().getMovies(title);
            call.enqueue(new Callback<MoviePager>() {
                @Override
                public void onResponse(Call<MoviePager> call, Response<MoviePager> response) {
                    if (!response.isSuccessful()) {
                        Log.e("APP_FAILURE", String.valueOf(response.code()));
                        return;
                    }
                    MoviePager moviePager = response.body();
                    ArrayList<Movie> movies = (ArrayList<Movie>) moviePager.getMovies();
                    recyclerView.setHasFixedSize(true);
                    mAdapter = new MoviesListAdapter(movies);

                    // use a linear layout manager
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(Call<MoviePager> call, Throwable t) {
                    Log.e("APP_FAILURE", t.getMessage());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.saved_movies:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.search_with_title:
                intent = new Intent(MainActivity.this, SearchWithTitleActivity.class);
                startActivity(intent);
                break;
            case R.id.search_with_director:
                intent = new Intent(MainActivity.this, SearchWithPersonActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class AsyncTaskParse extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            String text = strings[0];
            text = text.replace(" ", "%20");
            return httpClient.readMovieInfo(text);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            recyclerView.setHasFixedSize(true);
            mAdapter = new MoviesListAdapter(movies);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(mAdapter);

        }
    }
}
