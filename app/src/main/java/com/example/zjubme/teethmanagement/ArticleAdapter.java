package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView authorAndDate;
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(View view){
            super(view);
            authorAndDate = (TextView)view.findViewById(R.id.author_date);
            cardView = (CardView)view.findViewById(R.id.card_view);
            textView = (TextView)view.findViewById(R.id.TextView);
            imageView = (ImageView)view.findViewById(R.id.article_picture);
        }
    }

    public ArticleAdapter(List<Article> articles){
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(articles.get(i).getTitle());
        viewHolder.authorAndDate.setText(articles.get(i).getAuthor() + "\n" + articles.get(i).getDate());
        viewHolder.cardView.setId(i);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WebView.class);
                intent.putExtra("url", articles.get(view.getId()).getUrl());
                view.getContext().startActivity(intent);
            }
        });
        Picasso.get().load(articles.get(i).getImage()).resize(1024, 643).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
