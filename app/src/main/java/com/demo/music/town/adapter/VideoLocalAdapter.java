package com.demo.music.town.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.model.VideoEntity;
import com.demo.music.town.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoLocalAdapter extends RecyclerView.Adapter<VideoLocalAdapter.ViewHolder> {

    private List<VideoEntity> videoList;

    public VideoLocalAdapter(List<VideoEntity> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_local, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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
        @BindView(R.id.tv_size)
        TextView tvSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(itemView);
        }
    }
}
