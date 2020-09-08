package com.leknos.netflixroll.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.leknos.netflixroll.R;
import com.leknos.netflixroll.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.leknos.netflixroll.util.Constants.BASE_URL_IMG;
import static com.leknos.netflixroll.util.Constants.VIEW_HOLDER_COLOR;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {

    private int numberItems;
    private ArrayList<Movie> movies;
    private static int numberOfViewHolder;
    private OnMovieItemClickListener onMovieItemClickListener;

    public interface OnMovieItemClickListener {
        void onMovieItemClick(int position);
    }

    public void setOnMovieItemClickListener(OnMovieItemClickListener listener){
        onMovieItemClickListener = listener;
    }

    public MoviesListAdapter(ArrayList<Movie> movies) {
        this.numberItems = movies.size();
        this.movies = movies;
        numberOfViewHolder = 0;
    }

    public MoviesListAdapter(){

    }

    public void setMovies(List<Movie> movies) {
        this.movies = (ArrayList<Movie>) movies;
        this.numberItems = movies.size();
        notifyDataSetChanged();
    }

    public Movie getMovieAt(int position){
        return movies.get(position);
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        MoviesListViewHolder moviesListViewHolder = new MoviesListViewHolder(view);
        if(numberOfViewHolder%2 == 0)
            moviesListViewHolder.constraintLayout.setBackgroundColor(Color.parseColor(VIEW_HOLDER_COLOR));
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
        ConstraintLayout constraintLayout;
        TextView movieId;
        TextView movieTitle;
        TextView movieTextData;
        TextView movieVoteAverage;
        TextView movieOverview;
        ImageView movieImage;

        public MoviesListViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.movie_list_item__id);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieTextData = itemView.findViewById(R.id.movie_textData);
            movieVoteAverage = itemView.findViewById(R.id.movie_voteAverage);
            movieOverview = itemView.findViewById(R.id.movie_overview);
            movieImage = itemView.findViewById(R.id.movie_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieItemClickListener.onMovieItemClick(getAdapterPosition());
                }
            });
        }

        void bind(int listIndex){
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
