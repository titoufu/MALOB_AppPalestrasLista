package com.titoufu.lista_palestras;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myviewholder> {

    Context context;
    ArrayList<News> newsArrayList;

    public MyAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_palestra, parent, false);
        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Myviewholder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF61D7FF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF15C1FF"));
        }
        News news = newsArrayList.get(position);
        holder.nomeData.setText(news.dataPalestra);
        holder.nomeOrador.setText(news.oradorPalestra);
        holder.nomeAno.setText(news.anoPalestra);
        holder.nomeTema.setText(news.temaPalestra);
        holder.nomeSemana.setText(news.semanaPalestra);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {
        // elementos da lista
        //
        TextView nomeTema, nomeOrador, nomeAno, nomeData,nomeSemana;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            nomeAno = itemView.findViewById(R.id.id_campoData);
            nomeOrador = itemView.findViewById(R.id.id_campoOrador);
            nomeData = itemView.findViewById(R.id.id_campoAno);
            nomeTema = itemView.findViewById(R.id.id_campoTema);
            nomeSemana=itemView.findViewById(R.id.id_diaSemana);

        }
    }
}
