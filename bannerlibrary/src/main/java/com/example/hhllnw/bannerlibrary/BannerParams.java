package com.example.hhllnw.bannerlibrary;

import java.util.List;

/**
 * Created by hhl on 2016/12/16.
 * Bannner参数
 */

public class BannerParams {
    private List<? extends BannerEntity> paths;
    private BannerView.OnBannerItemClickListener listener;
    private int from;
    private int part = 1;
    private int dotSelectColorId;
    private int dotUnSelectColorId;
    private int radius = 10;
    private int interval = 5;//时间间隔,单位s

    public List<? extends BannerEntity> getPaths() {
        return paths;
    }

    public void setPaths(List<? extends BannerEntity> paths) {
        this.paths = paths;
    }

    public BannerView.OnBannerItemClickListener getListener() {
        return listener;
    }

    public void setListener(BannerView.OnBannerItemClickListener listener) {
        this.listener = listener;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getDotSelectColorId() {
        return dotSelectColorId;
    }

    public void setDotSelectColorId(int dotSelectColorId) {
        this.dotSelectColorId = dotSelectColorId;
    }

    public int getDotUnSelectColorId() {
        return dotUnSelectColorId;
    }

    public void setDotUnSelectColorId(int dotUnSelectColorId) {
        this.dotUnSelectColorId = dotUnSelectColorId;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
