package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    public class CommentListRespond {
        @SerializedName("comments")
        @Expose
        private List<Comment> comments = new ArrayList<>();

        public List<Comment> getComments() {
            return comments;
        }
    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("authorLink")
    @Expose
    private String authorLink;

    @SerializedName("authorThumb")
    @Expose
    private String authorThumb;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("likes")
    @Expose
    private int likes;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    @SerializedName("edited")
    @Expose
    private boolean edited;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public String getAuthorThumb() {
        return authorThumb;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public String getTime() {
        return time;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isEdited() {
        return edited;
    }
}
