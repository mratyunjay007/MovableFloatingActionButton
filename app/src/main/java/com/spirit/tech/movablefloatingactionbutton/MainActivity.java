package com.spirit.tech.movablefloatingactionbutton;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image;

    private int xDelta;
    private int yDelta;
    float dX,dY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.image);

        image.setOnTouchListener(onTouchListener());
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
           int lastAction;

            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            int screenHight = displaymetrics.heightPixels;
            int screenWidth = displaymetrics.widthPixels;

            @SuppressLint("NewApi") @Override
            public boolean onTouch(View view, MotionEvent event) {


                float newX, newY;

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:

                        newX = event.getRawX() + dX;
                        newY = event.getRawY() + dY;

                        // check if the view out of screen
                        if ((newX <= 0 || newX >= screenWidth-view.getWidth()) || (newY <= 0 || newY >= screenHight-view.getHeight()))
                        {     lastAction = MotionEvent.ACTION_MOVE;
                            break;
                        }

                        view.setX(newX);
                        view.setY(newY);

                        lastAction = MotionEvent.ACTION_MOVE;

                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastAction == MotionEvent.ACTION_DOWN)
                            Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return false;
                }
                return true;
            }
        };
    }
}
