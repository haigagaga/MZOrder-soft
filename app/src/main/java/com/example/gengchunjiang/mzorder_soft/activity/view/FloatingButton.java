package com.example.gengchunjiang.mzorder_soft.activity.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.gengchunjiang.mzorder_soft.R;

/**
 * Created by gengchunjiang on 2017/3/14.
 */

public class FloatingButton extends View {
    Bitmap button;
    float currentX,currentY;


    public FloatingButton(Context context) {
        super(context);
//        button = BitmapFactory.decodeResource(context.getResources(),
//               R.drawable.gouwu );
        setFocusable(true);
    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawBitmap(button,currentX,currentY,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //当前组件的两个属性
        this.currentX = event.getX();
        this.currentY = event.getY();
        //重绘
        this.invalidate();
        return true;
    }
}
