# BannerDemo
"无限循环"广告轮播</br>
先看看效果图</br>
![image](https://github.com/hhllnw/BannerDemo/raw/master/app/src/main/res/drawable/banner.gif)
```Java
 使用方法：
 BannerParams bannerParams = new BannerParams();
        bannerParams.setPaths(banners);//图片地址集合
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
    
    需要注意的是mBannerView在布局中父类必须是LinearLayout
    
```

```Java

Step 1:Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency
	dependencies {
	        compile 'com.github.hhllnw:BannerDemo:v1.4'
	}
```
