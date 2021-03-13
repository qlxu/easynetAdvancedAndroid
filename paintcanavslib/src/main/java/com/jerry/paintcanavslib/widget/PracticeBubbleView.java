package com.jerry.paintcanavslib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jerry.paintcanavslib.R;

public class PracticeBubbleView extends View {
    private Context mContext;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mStatus;
    private static int STATUS_IN = 1;   //在区域里面
    private static int STATUS_OUT = 2;  //在区域外
    private float mCurrentPosX;
    private float mCurrentPosY;
    private int offset = 0;
    private int dragx;
    private int dragy;

    public PracticeBubbleView(Context context) {
        super(context);
        init(context);
    }

    public PracticeBubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.dog);
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dragx = getWidth()/2;
        dragy = getHeight()/2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mStatus == STATUS_IN){
            canvas.drawBitmap(mBitmap,mCurrentPosX-mBitmap.getWidth()/2,mCurrentPosY-mBitmap.getHeight()/2,mPaint);
        }else {
            int dst_left = getWidth()/2-mBitmap.getWidth()/2;
            int dst_top = getHeight()/2-mBitmap.getHeight()/2;
            int dst_right = getWidth()/2+mBitmap.getWidth()/2;
            int dst_bottom = getHeight()/2+mBitmap.getHeight()/2;
            canvas.drawBitmap(mBitmap,new Rect(0,0,mBitmap.getWidth(),mBitmap.getHeight()),
                    new Rect(dst_left,dst_top,
                            dst_right,dst_bottom),mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mCurrentPosX = event.getX();
        mCurrentPosY = event.getY();
        getStatus(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(mStatus == STATUS_IN){
                dragx = (int) mCurrentPosX;
                dragy = (int) mCurrentPosY;
                getStatus(event);
                invalidate();
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(mStatus == STATUS_IN){
                Toast.makeText(mContext,"来摸摸我吧，亲！",Toast.LENGTH_SHORT).show();
            }else if(mStatus == STATUS_OUT){
                Toast.makeText(mContext,"亲，要带我去哪里嘛？",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void getStatus(MotionEvent event){
        float x = Math.abs(mCurrentPosX-dragx);
        float y = Math.abs(mCurrentPosY-dragy);
        if(x<=offset+mBitmap.getWidth()/2 && y<=offset+mBitmap.getHeight()/2){
            mStatus = STATUS_IN;
        }else {
            mStatus = STATUS_OUT;
        }
    }
}
