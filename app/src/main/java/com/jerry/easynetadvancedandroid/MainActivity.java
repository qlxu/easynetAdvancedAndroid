package com.jerry.easynetadvancedandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jerry.paintcanavslib.widget.JerryPaint;
import com.jerry.paintcanavslib.widget.PathView;
import com.jerry.paintcanavslib.widget.PracticeBubbleView;
import com.jerry.paintcanavslib.widget.PracticePaintView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(new JerryPaint(this));
        setContentView(new PracticePaintView(this));
//        setContentView(new PracticeBubbleView(this));
    }
}
