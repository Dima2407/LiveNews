package com.dimazatolokin.livenews.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dimazatolokin.livenews.R;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.util.Utils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class MainListNewsAdapter extends RecyclerView.Adapter<MainListNewsAdapter.VH> {

    private Context context;
    private List<Article> items;

    public MainListNewsAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Article> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_news_list, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.apply(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.imgImage)
        ImageView imgImage;
        @BindView(R.id.tvSource)
        TextView tvSource;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void apply(Article article) {
            Glide.with(context)
                    .load(article.getUrlToImage())
                    .into(imgImage);
            tvSource.setText(article.getSource().getName());
            String publishedAt = article.getPublishedAt();
            tvDate.setText(Utils.getPrettyTime(publishedAt));
            tvTitle.setText(article.getTitle());
            tvAuthor.setText(article.getAuthor());
        }
    }
}
