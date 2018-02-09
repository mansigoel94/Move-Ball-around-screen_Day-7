package com.example.mansigoel.dragbaseball;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainTouchActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        move = (ImageView) findViewById(R.id.image);
        main = (RelativeLayout) findViewById(R.id.main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        windowWidth = metrics.widthPixels;
        windowHeight = metrics.heightPixels;

//        imageWidth = move.getWidth();
//        imageHeight = move.getHeight();
        Log.d("Mansi", "onTouch: windowWidth" + windowWidth);
        Log.d("Mansi", "onTouch: windwHeight" + windowHeight);

        move.setOnTouchListener(OnTouchView());
    }

    private View.OnTouchListener OnTouchView() {
        return new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int x = (int) motionEvent.getRawX();
                final int y = (int) motionEvent.getRawY();
                int action = motionEvent.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        //user touches screen
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        xDelta = x - layoutParams.leftMargin;
                        yDelta = y - layoutParams.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //user is moving object
                        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                  /*      Log.d("Mansi", "onTouch: x-xDelta " + (x - xDelta));
                        Log.d("Mansi", "onTouch: y-yDelta " + (y - yDelta));
                        Log.d("Mansi", "onTouch: xDelta " + xDelta);
                        Log.d("Mansi", "onTouch: yDelta " + yDelta);
                        Log.d("Mansi", "onTouch: x " + x);
                        Log.d("Mansi", "onTouch: y " + y);*/
                        //user is at top left corner
                        if (x - xDelta < 0 && y - yDelta < 0) {
                            //reached left boundary and top boundary
                            layoutParams1.topMargin = -1;
                            layoutParams1.bottomMargin = 0;
                            layoutParams1.leftMargin = -1;
                            layoutParams1.rightMargin = 0;
                            view.setLayoutParams(layoutParams1);
                            break;
                        } else {
                            if (x - xDelta < 0) {
                                //reached left boundary
                                layoutParams1.topMargin = y - yDelta;
                                layoutParams1.bottomMargin = 0;
                                layoutParams1.leftMargin = -1;
                                layoutParams1.rightMargin = 0;
                                view.setLayoutParams(layoutParams1);
                                break;
                            }
                            if (y - yDelta < 0) {
                                //reached top boundary
                                layoutParams1.topMargin = -1;
                                layoutParams1.bottomMargin = 0;
                                layoutParams1.leftMargin = x - xDelta;
                                layoutParams1.rightMargin = 0;
                                view.setLayoutParams(layoutParams1);
                                break;
                            }
                        }

                        //user is at bottom right corner
                        if (x + xDelta > windowWidth && y + yDelta > windowHeight) {
                            //reached right boundary and bottom boundary
                            layoutParams1.topMargin = windowHeight - imageHeight;
                            layoutParams1.bottomMargin = windowHeight;
                            layoutParams1.leftMargin = windowWidth - imageWidth;
                            layoutParams1.rightMargin = windowWidth;
                            view.setLayoutParams(layoutParams1);
                            Log.d("Mansi", "onTouch: bottm right");
                            break;
                        } else {
                            if (x + xDelta > windowWidth) {
                                //reached right boundary
                                layoutParams1.topMargin = y - yDelta;
                                layoutParams1.bottomMargin = 0;
                                layoutParams1.leftMargin = windowWidth - imageWidth;
                                layoutParams1.rightMargin = windowWidth;
                                view.setLayoutParams(layoutParams1);
                                Log.d("Mansi", "onTouch: right");
                                break;
                            }
                            if (y + yDelta > windowHeight) {
                                //reached bottom boundary
                                layoutParams1.topMargin = windowHeight - imageHeight;
                                layoutParams1.bottomMargin = windowHeight;
                                layoutParams1.leftMargin = x - xDelta;
                                layoutParams1.rightMargin = 0;
                                Log.d("Mansi", "onTouch: bottom");
                                view.setLayoutParams(layoutParams1);
                                break;
                            }
                        }
                        layoutParams1.leftMargin = x - xDelta;
                        layoutParams1.topMargin = y - yDelta;
                        layoutParams1.rightMargin = 0;
                        layoutParams1.bottomMargin = 0;
                        view.setLayoutParams(layoutParams1);
                        break;
                    case MotionEvent.ACTION_UP:
                        //use has moved his finger up from screen releasing object
                        Toast.makeText(getBaseContext(), "New position: x=" + x + " and y=" + y, Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d("Mansi", "onTouch: Moved outside");
                        break;
                }
                main.invalidate();
                return true;
            }
        };
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        imageWidth = move.getWidth();
        imageHeight = move.getHeight();
        Log.d("Mansi", "image width: " + imageWidth);
        Log.d("Mansi", "image height: " + imageHeight);
    }
}