package com.example.mathieu.drawapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author: Created by Mathieu on 2017-03-08.
 */

public class DrawingPage extends LinearLayout {

    DrawingView drawView;
    LinearLayout layoutBtn;
    Button btnRetry;
    EditText txtEquation;

    public DrawingPage(Context context) {
        super(context);
    }

    public DrawingPage(Context context, AttributeSet attrs) {
        super(context, attrs);


        drawView = new DrawingView(context, attrs);
        drawView.setLayoutParams(new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f));

        txtEquation = new EditText(context);
        txtEquation.setLayoutParams(new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.5f));

        btnRetry = new Button(context);
        btnRetry.setText(R.string.clear);




        //Creation of the button layout
        layoutBtn = new LinearLayout(context);
        layoutBtn.setOrientation(LinearLayout.HORIZONTAL);
        layoutBtn.addView(btnRetry);



        setOrientation(LinearLayout.HORIZONTAL);
        addViewsInThePage(true);


        //setting the button click listener
        btnRetry.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                drawView.clear();
            }
        });
    }

    public void addViewsInThePage(Boolean right){

        removeView(txtEquation);
        removeView(drawView);
        removeView(layoutBtn);

        if(right){
            addView(txtEquation);
            addView(drawView);
            addView(layoutBtn);
        }else{
            addView(layoutBtn);
            addView(drawView);
            addView(txtEquation);
        }


    }
}
