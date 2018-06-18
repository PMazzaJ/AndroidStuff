package com.example.android.movieproject.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDbHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "favoriteMovies.db";
    public static final int DATABASE_VERSION = 1;



    //construtor
    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        final String SQL_CREATE_TABLE = "CREATE TABLE " + MoviesContract.MoviesEntry.TABLE_NAME + " (" +
                MoviesContract.MoviesEntry._ID + "INTEGER PRIMARY KEY," +
                MoviesContract.MoviesEntry.COLUMN_MOVIE_ID + "INTEGER," +
                MoviesContract.MoviesEntry.COLUMN_TITLE + "TEXT NOT NULL," +
                MoviesContract.MoviesEntry.COLUMN_POSTER_PATH + "TEXT NOT NULL," +
                MoviesContract.MoviesEntry.COLUMN_RATING + "REAL NOT NULL," +
                MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE + "TEXT NOT NULL," +
                MoviesContract.MoviesEntry.COLUMN_SYNOPSIS + "TEXT NOT NULL" +

                ");";

        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
        onCreate(db);
    }






 






    }



