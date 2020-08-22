package com.leknos.netflixroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leknos.netflixroll.MainActivity;
import com.leknos.netflixroll.MoviesListAdapter;
import com.leknos.netflixroll.R;
import com.leknos.netflixroll.model.Movie;
import com.leknos.netflixroll.model.MovieDetails;
import com.leknos.netflixroll.model.MovieDetailsPager;
import com.leknos.netflixroll.model.MoviePager;
import com.leknos.netflixroll.utils.NetworkService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.leknos.netflixroll.utils.Constants.BASE_URL_IMG;
import static com.leknos.netflixroll.utils.Constants.W500_URL_IMG;

public class MovieDetailsActivity extends AppCompatActivity{

    private TextView textView;
    private TextView description;
    private TextView voteAverage;
    private TextView textData;
    private ImageView imageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.fragment_search_with_title__textView);
        description = findViewById(R.id.activity_movie_details__movie_overview);
        imageView = findViewById(R.id.activity_movie_details__movie_image);
        voteAverage = findViewById(R.id.activity_movie_details__movie_voteAverage);
        textData = findViewById(R.id.activity_movie_details__movie_textData);
        int movieId = getIntent().getExtras().getInt("MOVIE_ID");
        postRequest(String.valueOf(movieId));
    }

    public void postRequest(String movieId) {
        if (!movieId.equals("")) {
            String title = movieId;
            Call<MovieDetails> call = NetworkService.getInstance().getJSONApi().getDetailMovie(title);
            call.enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                    if (!response.isSuccessful()) {
                        Log.e("APP_FAILURE", String.valueOf(response.code()));
                        return;
                    }
                    MovieDetails movie = response.body();
                    toolbar.setTitle(movie.getTitle());
                    description.setText(movie.getOverview());
                    voteAverage.setText(String.valueOf(movie.getVoteAverage()));
                    textData.setText(movie.getReleaseDate());
                    Picasso.get()
                            .load(W500_URL_IMG+movie.getBackdropPath())
                            .placeholder(R.drawable.no_poster)
                            .fit()
                            .centerCrop()
                            .into(imageView);

                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t) {
                    Log.e("APP_FAILURE", t.getMessage());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_movie_details__save:
                intent = new Intent(MovieDetailsActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}