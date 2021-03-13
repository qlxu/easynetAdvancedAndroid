package com.jerry.paintcanavslib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jerry.paintcanavslib.R;


/**
 * path路径
 */

public class PathView extends View {
    private final Context mContext;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;

    public PathView(Context context) {
        super(context);
        mContext = context;
        init();
    }


    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mContext = context;
    }


    private void init() {
        mBitmap = BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.geostar_guide_1)
        .copy(Bitmap.Config.ARGB_8888,true);
//        if(null != mBitmap)
            mCanvas = new Canvas();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if(null != mCanvas)
//        canvas.drawRect(10,50,100,100,mPaint);
//        canvas.drawCircle(500,500,500,mPaint);

        canvas.drawBitmap(mBitmap,0,0,mPaint);
    }
}
