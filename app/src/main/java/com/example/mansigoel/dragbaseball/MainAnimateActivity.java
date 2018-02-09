package com.example.mansigoel.dragbaseball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainAnimateActivity extends AppCompatActivity implements View.OnTouchListener {

    int total, failure = 0;
    private float x_cord;
    public float y_cord;
    private int xDelta;
    private int yDelta;
    private ImageView move;
    private RelativeLayout main;
    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;
    private int windowWidth;
    private int windowHeight;
    private int imageWidth;
    private int imageHeight;
    float dX, dY;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        move = (ImageView) findViewById(R.id.image);
        main = (RelativeLayout) findViewById(R.id.main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        windowWidth = metrics.widthPixels;
        windowHeight = metrics.heightPixels;

        Log.d("Mansi", "onTouch: windowWidth" + windowWidth);
        Log.d("Mansi", "onTouch: windwHeight" + windowHeight);

        move.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = view.getX() - motionEvent.getRawX();
                dY = view.getY() - motionEvent.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }
}
