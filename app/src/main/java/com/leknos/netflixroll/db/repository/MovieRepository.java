package com.leknos.netflixroll.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.leknos.netflixroll.db.MovieDao;
import com.leknos.netflixroll.db.MovieDatabase;
import com.leknos.netflixroll.model.Movie;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void insert(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void update(Movie movie) {
        new UpdateMovieAsyncTask(movieDao).execute(movie);
    }

    public void delete(Movie movie) {
        new DeleteMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAllMovies() {
        new DeleteAllMoviesAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

    public static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }

    public static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        MovieDao movieDao;

        private UpdateMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.update(movies[0]);
            return null;
        }
    }

    public static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }

    public static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void>{
        MovieDao movieDao;

        private DeleteAllMoviesAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAllMovies();
            return null;
        }
    }



}
