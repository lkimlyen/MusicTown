package com.demo.architect.data.model;

public class SongEntity {
    private int id;
    private String title;
    private String artist;
    private String name;
    private long duration;

    public SongEntity(int id, String title, String artist, String name, long duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }
}
