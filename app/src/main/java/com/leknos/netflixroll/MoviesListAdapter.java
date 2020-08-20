package com.leknos.netflixroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leknos.netflixroll.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.leknos.netflixroll.utils.Constants.BASE_URL_IMG;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {

    private int numberItems;
    private ArrayList<Movie> movies;
    private static int numberOfViewHolder;

    public MoviesListAdapter(ArrayList<Movie> movies) {
        this.numberItems = movies.size();
        this.movies = movies;
        numberOfViewHolder = 0;
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        MoviesListViewHolder moviesListViewHolder = new MoviesListViewHolder(view);
        moviesListViewHolder.viewHolderId.setText(String.valueOf(numberOfViewHolder));
        numberOfViewHolder++;
        return moviesListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class MoviesListViewHolder extends RecyclerView.ViewHolder{

        TextView movieId;
        TextView movieTitle;
        TextView movieTextData;
        TextView movieVoteAverage;
        TextView movieOverview;
        TextView viewHolderId;
        ImageView movieImage;

        public MoviesListViewHolder(@NonNull View itemView) {
            super(itemView);

            movieId = itemView.findViewById(R.id.movie_id);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieTextData = itemView.findViewById(R.id.movie_textData);
            movieVoteAverage = itemView.findViewById(R.id.movie_voteAverage);
            movieOverview = itemView.findViewById(R.id.movie_overview);
            viewHolderId = itemView.findViewById(R.id.view_holder_id);
            movieImage = itemView.findViewById(R.id.movie_image);
        }

        void bind(int listIndex){
            movieId.setText("id: "+String.valueOf(movies.get(listIndex).getId()));
            movieTitle.setText(movies.get(listIndex).getTitle());
            movieTextData.setText(movies.get(listIndex).getTextData());
            movieVoteAverage.setText(String.valueOf(movies.get(listIndex).getVoteAverage()));
            movieOverview.setText(movies.get(listIndex).getOverview());
            Picasso.get()
                    .load(BASE_URL_IMG+movies.get(listIndex).getPosterPath())
                    .placeholder(R.drawable.no_poster)
                    .into(movieImage);
        }
    }
}
