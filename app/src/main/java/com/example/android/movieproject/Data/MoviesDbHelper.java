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






    /*
    public void AddFavorite(Filme filme){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MoviesContract.MoviesEntry.COLUMN_MOVIE_ID, filme.getId());
        cv.put(MoviesContract.MoviesEntry.COLUMN_TITLE, filme.getOriginalTitle());
        cv.put(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH, filme.getPosterPath());
        cv.put(MoviesContract.MoviesEntry.COLUMN_RATING, filme.getVoteAverage());
        cv.put(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE, filme.getReleaseDate());
        cv.put(MoviesContract.MoviesEntry.COLUMN_SYNOPSIS, filme.getOverview());

        db.insert(MoviesContract.MoviesEntry.TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteFavorite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MoviesContract.MoviesEntry.TABLE_NAME, MoviesContract.MoviesEntry.COLUMN_MOVIE_ID + "=" + id, null);

    }

    public List<Filme> getAllFavorite(){
        String[] columns = {
                MoviesContract.MoviesEntry._ID,
                MoviesContract.MoviesEntry.COLUMN_MOVIE_ID,
                MoviesContract.MoviesEntry.COLUMN_TITLE,
                MoviesContract.MoviesEntry.COLUMN_POSTER_PATH,
                MoviesContract.MoviesEntry.COLUMN_RATING,
                MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE,
                MoviesContract.MoviesEntry.COLUMN_SYNOPSIS
        };



        List<Filme> favoriteList = new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();

        Cursor cursor = db.query(MoviesContract.MoviesEntry.TABLE_NAME, columns, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do{
                Filme filme = new Filme();
                filme.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_MOVIE_ID))));
                filme.setOriginalTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TITLE)));
                filme.setPosterPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH)));
                filme.setVoteAverage(Double.parseDouble(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RATING))));
                filme.setReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE)));
                filme.setOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_SYNOPSIS)));

                favoriteList.add(filme);

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

    */






    }



