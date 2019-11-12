package com.demo.music.town.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistMoreAdapter extends RecyclerView.Adapter<PlaylistMoreAdapter.ViewHolder> {
    private List<TrendingVideoCategory> trendingVideoList;
    private List<ArtistCategory> artistCategoryList;
    private String displayType;
    private OnItemClickListener listener;

    public PlaylistMoreAdapter(List<TrendingVideoCategory> trendingVideoList, List<ArtistCategory> artistCategoryList, String displayType, OnItemClickListener listener) {
        this.trendingVideoList = trendingVideoList;
        this.artistCategoryList = artistCategoryList;
        this.displayType = displayType;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_more, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if (displayType.equals(Constants.POPULAR)) {
            TrendingVideoCategory item = trendingVideoList.get(position);
            holder.tvPosition.setText(String.valueOf(position + 1));
            holder.tvName.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemTrendingCategory(item);
                    }
                }
            });
        } else {
            ArtistCategory item = artistCategoryList.get(position);
            holder.tvPosition.setText(String.valueOf(position + 1));
            holder.tvName.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                       listener.onItemArtistCategory(item);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return displayType.equals(Constants.POPULAR) ? trendingVideoList.size() : artistCategoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_position)
        TextView tvPosition;

        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemTrendingCategory(TrendingVideoCategory trendingVideoCategory);

        void onItemArtistCategory(ArtistCategory trendingVideoCategory);
    }
}
