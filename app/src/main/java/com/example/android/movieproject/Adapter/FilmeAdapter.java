package com.example.android.movieproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.movieproject.DetalhesFilme;
import com.example.android.movieproject.Model.Filme;
import com.example.android.movieproject.R;

import java.util.List;



public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.MyViewHolder> {

    private  Context mContext;
    private  List<Filme> mData;

    public FilmeAdapter(Context mContext, List<Filme> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public FilmeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Glide.with(mContext).load(mData.get(position).getPosterPath()).into(holder.img_filme_Thumbnail);

        //clica no poster
        // envia os dados para DetalhesFilme
        holder.cv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetalhesFilme.class);

                intent.putExtra("nomeFilme", mData.get(position).getOriginalTitle());
                intent.putExtra("rating", mData.get(position).getVoteAverage());
                intent.putExtra("thumbnail", mData.get(position).getPosterPath());
                intent.putExtra("releaseDate", mData.get(position).getReleaseDate());
                intent.putExtra("plot",mData.get(position).getOverview());
                intent.putExtra("idFilme",mData.get(position).getId());





                mContext.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ImageView img_filme_Thumbnail;
        final CardView cv_view;

        MyViewHolder(View itemView) {
            super(itemView);

            img_filme_Thumbnail = itemView.findViewById(R.id.imagem_filme_id);
            cv_view = itemView.findViewById(R.id.cv_id);



        }
    }

}




