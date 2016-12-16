package com.example.hhllnw.bannerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hhllnw.bannerlibrary.BannerParams;
import com.example.hhllnw.bannerlibrary.BannerEntity;
import com.example.hhllnw.bannerlibrary.BannerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BannerView mBannerView;
    private ArrayList<BannerEntity> banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBannerView = (BannerView) findViewById(R.id.mBannerView);

        banners = new ArrayList<>();
        banners.add(new BannerEntity("http://pic24.nipic.com/20121023/5692504_113455637193_2.jpg"));
        banners.add(new BannerEntity("http://pic63.nipic.com/file/20150328/9448607_153955535000_2.jpg"));
        banners.add(new BannerEntity("http://tupian.enterdesk.com/2013/mxy/12/16/4/6.jpg"));

        BannerParams bannerParams = new BannerParams();
        bannerParams.setPaths(banners);//图片地址结合
        bannerParams.setFrom(BannerView.FROM_NET);//图片类型（本地,网络）
        bannerParams.setPart(2);//Banner高度，2 表示屏幕高度 1/2
        bannerParams.setDotSelectColorId(R.color.dot_selected);//点选中颜色
        bannerParams.setDotUnSelectColorId(R.color.dot_unSelect);//未选中颜色
        bannerParams.setRadius(10);//点半径
        bannerParams.setInterval(5);//时间间隔
        bannerParams.setListener(new BannerView.OnBannerItemClickListener() {
            @Override
            public void onBannerClick(int index, BannerEntity banner) {
                //item单击事件
            }
        });
        mBannerView.setUpData(bannerParams);

        mBannerView.onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBannerView != null) {
            mBannerView.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBannerView != null) {
            mBannerView.onStop();
        }
    }
}
