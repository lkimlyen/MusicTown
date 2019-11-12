package com.demo.music.town.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<TrendingVideoCategory> trendingVideoList;
    private int witdhItem;

    public PlaylistAdapter(List<TrendingVideoCategory> trendingVideoList, int witdhItem) {
        this.trendingVideoList = trendingVideoList;
        this.witdhItem = witdhItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TrendingVideoCategory item = trendingVideoList.get(position);
        Picasso.get().load(item.getThumbnailUrl()).resize(witdhItem,witdhItem*9/16).into(holder.ivCover, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.GONE);
            }
        });
        holder.tvName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return trendingVideoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
