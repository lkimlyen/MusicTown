package com.demo.music.town.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class TrendingVideoAdapter extends RecyclerView.Adapter<TrendingVideoAdapter.ViewHolder> {

    private List<TrendingVideo> videoList;

    private OnClickItemListener listener;

    public TrendingVideoAdapter(List<TrendingVideo> videoList, OnClickItemListener listener) {
        this.videoList = videoList;
        this.listener = listener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trending_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrendingVideo trendingVideo = videoList.get(position);
        Picasso.get().load(trendingVideo.getThumbnailUrl()).into(holder.ivThumb,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(GONE);

                    }

                    @Override
                    public void onError(Exception e) {
                        holder.progressBar.setVisibility(GONE);
                    }
                });
        holder.tvName.setText(trendingVideo.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickItem(trendingVideo);
                }
            }
        });

        holder.ibDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDownload(trendingVideo.getVideoUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumb)
        ImageView ivThumb;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_view)
        TextView tvView;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        @BindView(R.id.ib_download)
        ImageButton ibDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickItemListener {
        void onClickItem(TrendingVideo trendingVideo);

        void onDownload(String url);
    }
}
