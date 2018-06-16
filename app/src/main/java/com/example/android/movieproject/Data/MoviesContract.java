package com.example.android.movieproject.Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String AUTHORITY = "com.example.android.movieproject";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "movie";


    public static final class MoviesEntry implements BaseColumns{

        /*
        |--------------------------------------------------------------------------
        | Table
        |--------------------------------------------------------------------------
        */
        public static final String TABLE_NAME = "favoriteMovies";
        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "posterPath";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE_DATE = "YYYY-MM-DD";
        public static final String COLUMN_SYNOPSIS = "synopsiss";






        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();



    }







}
