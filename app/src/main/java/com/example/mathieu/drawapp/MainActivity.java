package com.example.mathieu.drawapp;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;
import java.util.jar.Manifest;


public class MainActivity extends AppCompatActivity {

    DrawingView drawingSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingSpace = (DrawingView) findViewById(R.id.drawingSpace);

        //preferences requesting if needed
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_NO_LOCALIZED_COLLATORS );
        }
    }

    public void clearDrawingSpace(View view) {
        drawingSpace.clear();
        }

}
