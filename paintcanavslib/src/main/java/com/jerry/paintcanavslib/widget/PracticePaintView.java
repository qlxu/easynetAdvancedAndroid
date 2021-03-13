package com.jerry.paintcanavslib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jerry.paintcanavslib.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class PracticePaintView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    public PracticePaintView(Context context) {
        super(context);
        init(context);
    }

    public PracticePaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
        ColorMatrix colorMatrix = new ColorMatrix();
        //原图色彩
//        colorMatrix.set(new float[]{
//                1, 0, 0, 0, 0,
//                0, 1, 0, 0, 0,
//                0, 0, 1, 0, 0,
//                0, 0, 0, 1, 0,
//        });
        //灰色效果
        colorMatrix.set(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        mPaint.setColorFilter(colorMatrixColorFilter);
        saveBitmap(mBitmap,"icon");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = getMatrix();
        canvas.drawBitmap(mBitmap,matrix,mPaint);
    }

    public void saveBitmap(Bitmap bitmap,String picName) {
        Log.e(TAG, "保存图片");
        File f = new File("/sdcard/namecard/", picName);
        if (f.exists()) {
            f.delete();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
