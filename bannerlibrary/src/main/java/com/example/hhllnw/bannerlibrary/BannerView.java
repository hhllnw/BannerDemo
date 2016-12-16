package com.example.hhllnw.bannerlibrary;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;


public class BannerView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private ViewPager mBannerPager;
    private DotView mBannerDotView;
    private BannerAdapter adapter;
    private int position;

    private static long BANNER_SCROLL_INTERVAL = 5 * 1000;//时间间隔
    public final static int FROM_LOCAL = 1000;//加載本地图片
    public final static int FROM_NET = 1001;//加载网络图片
    private BannerParams bannerParams;

    public BannerView(Context context) {
        super(context);
        setUpView(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void setUpView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.widget_banner_view, this);
        mBannerPager = (ViewPager) findViewById(R.id.mBannerPager);
        mBannerPager.addOnPageChangeListener(this);
        mBannerDotView = (DotView) findViewById(R.id.BannerDotView);
    }

    /**
     * 填充参数
     *
     * @param bannerParams
     */
    public void setUpData(BannerParams bannerParams) {
        this.bannerParams = bannerParams;
        if (bannerParams == null) {
            return;
        }
        if (bannerParams.getPaths() == null || bannerParams.getPaths() == null) {
            return;
        }
        if (!(this.getParent() instanceof android.widget.LinearLayout)) {
            Log.e("err", "BannerView parent must LinearLayout");
            return;
        }

        int height = (BannerUtilty.getScreenHeight(mContext) - BannerUtilty.getActionBarHeight(mContext)) / bannerParams.getPart();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, height);
        setLayoutParams(params);

        //时间间隔
        BANNER_SCROLL_INTERVAL = bannerParams.getInterval() * 1000;

        //设置点选中颜色和未选中颜色
        if (bannerParams.getRadius() == 10) {
            mBannerDotView.setDotColor(bannerParams.getDotSelectColorId(), bannerParams.getDotUnSelectColorId());
        } else {
            mBannerDotView.setDotColor(bannerParams.getDotSelectColorId(), bannerParams.getDotUnSelectColorId(), bannerParams.getRadius());
        }

        adapter = new BannerAdapter();
        mBannerPager.setAdapter(adapter);
        int half = Short.MAX_VALUE / 2;
        half = half - half % bannerParams.getPaths().size();
        mBannerPager.setCurrentItem(half);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (position < Short.MAX_VALUE - 1) {
                mBannerPager.setCurrentItem(position + 1, true);
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        handler.removeMessages(0);
        this.position = position;
        mBannerDotView.notifyDataChanged(position % bannerParams.getPaths().size(), bannerParams.getPaths().size());
        handler.sendEmptyMessageDelayed(0, BANNER_SCROLL_INTERVAL);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 开始轮播
     */
    public void onStart() {
        handler.removeMessages(0);
        handler.sendEmptyMessageDelayed(0, BANNER_SCROLL_INTERVAL);
    }

    /**
     * 停止轮播
     */
    public void onStop() {
        handler.removeMessages(0);
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Short.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final int index = position % bannerParams.getPaths().size();
            final BannerEntity pathEntity = bannerParams.getPaths().get(index);
            final ImageView imageView = new ImageView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //加载图片
            if (bannerParams.getFrom() == FROM_LOCAL) {
                Glide.with(mContext).load(pathEntity.getResId()).into(imageView);
            } else if (bannerParams.getFrom() == FROM_NET) {
                Glide.with(mContext).load(pathEntity.getPath()).into(imageView);
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bannerParams.getListener() != null) {
                        bannerParams.getListener().onBannerClick(index, pathEntity);
                    }
                }
            });
            container.addView(imageView, params);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public interface OnBannerItemClickListener {
        /**
         * item单击事件
         *
         * @param index  位置
         * @param banner BannerEntity实体
         */
        void onBannerClick(int index, BannerEntity banner);
    }


}
