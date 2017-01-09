package com.example.hhllnw.bannerlibrary;

/**
 * Created by hhl on 2016/12/16.
 */

public class BannerEntity {
    private int resId;
    private String path;

    public BannerEntity() {

    }

    public BannerEntity(int resId) {
        this.resId = resId;
    }

    public BannerEntity(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getResId() {
        return resId;
    }
}
