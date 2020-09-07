package com.leknos.netflixroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.leknos.netflixroll.R;
import com.leknos.netflixroll.db.MovieDao;
import com.leknos.netflixroll.db.MovieDatabase;
import com.leknos.netflixroll.model.Movie;
import com.leknos.netflixroll.model.MovieDetails;
import com.leknos.netflixroll.util.NetworkService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.leknos.netflixroll.util.Constants.W500_URL_IMG;

public class MovieDetailsActivity extends AppCompatActivity{

    private TextView textView;
    private TextView description;
    private TextView voteAverage;
    private TextView textData;
    private ImageView imageView;
    private Toolbar toolbar;
    private MovieDetails movie;

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
                    movie = response.body();
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

    private Movie getMovie(){
        int id = movie.getId();
        String title = movie.getTitle();
        String textData = movie.getReleaseDate();
        String posterPath = movie.getPosterPath();
        double voteAverage = movie.getVoteAverage();
        String overview = movie.getOverview();
        return new Movie(id, title, textData, posterPath, voteAverage, overview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_movie_details__save:
                MovieDao movieDao = MovieDatabase.getInstance(getApplicationContext()).movieDao();
                new InsertMovieAsyncTask(movieDao).execute(getMovie());
                //if(movie.getId() == )
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            Log.d("EXECUTEEXECUTE2", "doInBackground: EXECUTE");
            return null;
        }
    }
}