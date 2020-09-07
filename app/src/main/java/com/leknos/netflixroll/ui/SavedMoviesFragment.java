package com.leknos.netflixroll.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leknos.netflixroll.viewmodel.MovieViewModel;
import com.leknos.netflixroll.adapter.MoviesListAdapter;
import com.leknos.netflixroll.R;
import com.leknos.netflixroll.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SavedMoviesFragment extends Fragment {
    private MovieViewModel movieViewModel;
    private RecyclerView recyclerView;
    private MoviesListAdapter mAdapter;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MovieViewModel.class);
        return inflater.inflate(R.layout.fragment_saved_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_saved_movies__recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MoviesListAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnMovieItemClickListener(new MoviesListAdapter.OnMovieItemClickListener() {
            @Override
            public void onMovieItemClick(int position) {
                Movie movie = movies.get(position);
                Toast.makeText(getContext(), "id"+movie.getId()+" title"+movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
                intent.putExtra("MOVIE_ID", movie.getId());
                startActivity(intent);
            }
        });

        movieViewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                setMovies((ArrayList<Movie>) movies);
                mAdapter.setMovies(movies);
            }
        });



    }
}