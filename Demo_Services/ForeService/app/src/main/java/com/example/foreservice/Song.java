package com.example.foreservice;

import java.io.Serializable;

public class Song implements Serializable {
    private String title; // Tiêu đề bài hát
    private String singer; // Tên ca sĩ
    private int image; // Tài nguyên hình ảnh của bài hát
    private int resource; // Tài nguyên nhạc của bài hát

    // Constructor để khởi tạo các thuộc tính của lớp
    public Song(String title, String singer, int image, int resource) {
        this.title = title;
        this.singer = singer;
        this.image = image;
        this.resource = resource;
    }

    // Getter và setter cho thuộc tính title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter và setter cho thuộc tính singer
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    // Getter và setter cho thuộc tính image
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Getter và setter cho thuộc tính resource
    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
