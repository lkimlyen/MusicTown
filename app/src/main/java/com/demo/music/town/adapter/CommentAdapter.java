package com.demo.music.town.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.View.GONE;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> videoList;

    public CommentAdapter(List<Comment> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment trendingVideo = videoList.get(position);
        Picasso.get().load(trendingVideo.getAuthorThumb()).into(holder.ivAvatar);
        holder.tvName.setText(trendingVideo.getAuthor());
        holder.tvComment.setText(trendingVideo.getText());
        if (trendingVideo.isEdited()){
            holder.tvEdit.setVisibility(View.VISIBLE);
        }else {
            holder.tvEdit.setVisibility(GONE);
        }
        holder.tvLike.setText(String.valueOf(trendingVideo.getLikes()));
        holder.tvTime.setText(trendingVideo.getTime());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_avatar)
        CircleImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_like)
        TextView tvLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
