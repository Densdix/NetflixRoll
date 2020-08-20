package com.leknos.netflixroll.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leknos.netflixroll.MainActivity;
import com.leknos.netflixroll.MoviesListAdapter;
import com.leknos.netflixroll.R;
import com.leknos.netflixroll.model.Movie;
import com.leknos.netflixroll.model.MoviePager;
import com.leknos.netflixroll.utils.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchWithTitleFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoviesListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_with_title, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_search_with_title__recycler_view);
        FloatingActionButton fab = view.findViewById(R.id.fragment_search_with_title__fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final EditText input = new EditText(getContext());
                builder.setView(input);
                builder.setTitle("Search TV Show");

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                    layoutManager = new LinearLayoutManager(getContext());
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
}