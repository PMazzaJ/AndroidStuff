package com.example.android.movieproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.movieproject.Model.Review;
import com.example.android.movieproject.R;


import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Review> reviewList;


    public ReviewAdapter(Context mContext, List<Review> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_layout, viewGroup, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ReviewAdapter.MyViewHolder viewHolder, int i){

        viewHolder.TextView_Content.setText(reviewList.get(i).getContent());
        viewHolder.TextView_Author.setText("Author: " + reviewList.get(i).getAuthor());
        
    }

    @Override
    public int getItemCount(){
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView TextView_Content;
        public TextView TextView_Author;


        public MyViewHolder(View view) {
            super(view);

            TextView_Content = view.findViewById(R.id.reviewContent_textView);
            TextView_Author = view.findViewById(R.id.reviewAuthor_textView);



        }
    }




}
