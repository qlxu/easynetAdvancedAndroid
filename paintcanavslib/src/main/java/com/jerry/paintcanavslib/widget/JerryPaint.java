package com.jerry.paintcanavslib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class JerryPaint extends View {
    private Paint mPaint;
    private Context mContext;

    public JerryPaint(Context context) {
        super(context);
        init(context);
    }

    public JerryPaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100,100,300,300,mPaint);
    }
}
