package com.example.mathieu.drawapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.UUID;


/**
 * Created by Mathieu on 2017-02-22.
 */

public class DrawingView extends View {

    private float x;
    private float y;

    Paint drawPaint;
    private Path path = new Path();

    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    private Handler saveHandler;

    private Context context;



    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        drawPaint = new Paint(Paint.DITHER_FLAG);
        drawPaint.setAntiAlias(true);
        drawPaint.setColor(Color.parseColor("#000000"));
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStrokeWidth(30);
        setWillNotDraw(false);

        saveHandler = new Handler();
    }

    @Override
    protected void onSizeChanged(int w, int h, int width, int height) {
        super.onSizeChanged(w, h, width, height);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, drawPaint);
        canvas.drawPath(path, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(path, drawPaint);
                path.reset();
                break;
            default:
                return false;
        }
        invalidate();
        saveHandler.removeCallbacks(run);
        saveHandler.postDelayed(run, 2000);
        return true;
    }

    public void clear(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
        saveHandler.removeCallbacks(run);
    }

    public  void saveCharacter(){
        this.setDrawingCacheEnabled(true);

        String savedCharacter = MediaStore.Images.Media.insertImage(
                context.getContentResolver(), this.getDrawingCache(),
                UUID.randomUUID().toString()+".png", "drawing");

        if(savedCharacter!=null){
            Toast savedToast = Toast.makeText(context,
                    savedCharacter, Toast.LENGTH_SHORT);
            savedToast.show();
        }
        else{
            Toast unsavedToast = Toast.makeText(context,
                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
            unsavedToast.show();
        }
    }


    private Runnable run = new Runnable() {
        @Override
        public void run() {
            saveCharacter();
            clear();
        }
    };
}
