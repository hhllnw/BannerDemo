package com.example.hhllnw.bannerlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class DotView extends View {

    private float density;
    private int width;
    private float radius;
    private float padding;
    private int position;
    private int count;
    private float mStartX;
    private Paint paint;
    private int selectedColor;
    private int unselectedColor;

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public DotView(Context context) {
        super(context);
        initializeView();
    }

    private void initializeView() {
        width = getResources().getDisplayMetrics().widthPixels;
        density = getResources().getDisplayMetrics().density;
        paint = new Paint();
    }

    /**
     * 设置点选中颜色和未选中状态
     *
     * @param selectedColor
     * @param unselectedColor
     */
    public void setDotColor(int selectedColor, int unselectedColor) {
        setDotColor(selectedColor, unselectedColor, 10);
    }

    /**
     * 设置点选中颜色和未选中状态及点的半径
     *
     * @param selectedColor
     * @param unselectedColor
     * @param radius
     */
    public void setDotColor(int selectedColor, int unselectedColor, int radius) {
        this.selectedColor = getResources().getColor(selectedColor);
        this.unselectedColor = getResources().getColor(unselectedColor);
        this.radius = radius;
        this.padding = radius * density;
    }

    public void notifyDataChanged(int position, int count) {
        this.position = position;
        this.count = count;
        mStartX = (width - ((count - 1) * padding + count * 2 * radius)) / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < count; i++) {
            if (position == i) {
                paint.setColor(selectedColor);
            } else {
                paint.setColor(unselectedColor);
            }
            canvas.drawCircle(mStartX + i * padding + i * 2 * radius, radius, radius, paint);
        }
    }
}
