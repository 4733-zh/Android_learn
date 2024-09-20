package com.example.drawabletest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;

public class MyGradinentDrawable extends GradientDrawable {


    private Paint mPaint;

    public MyGradinentDrawable() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        float radius = 20;//这里随意写的
        CornerPathEffect corEffect = new CornerPathEffect(radius);
        mPaint.setPathEffect(corEffect);
    }

    public MyGradinentDrawable(Orientation orientation, int[] colors) {
        super(orientation, colors);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect rect = getBounds();
        int left = rect.left;
        int right = rect.right;
        int top = rect.top;
        int bottom = rect.bottom;

        int width = right - left;
        int height = bottom - top;
        int lenght = getTanLenght(65, height);
        Point p0 = new Point(left, bottom);
        Point p1 = new Point(lenght, top);
        Point p2 = new Point(right, top);
        Point p3 = new Point(right - lenght, bottom);

        Path path = new Path();
        path.moveTo(p0.x,p0.y);
        path.lineTo(p1.x,p1.y);
        path.lineTo(p2.x,p2.y);
        path.lineTo(p3.x,p3.y);
        path.close();
        canvas.drawPath(path, mPaint);
//        canvas.drawText("自定义GradientDrawable",);
    }

    public static int getTanLenght(int angle, int lenght) {
        double var = Math.toRadians(angle);
        return (int) (lenght / Math.tan(var));
    }
}
