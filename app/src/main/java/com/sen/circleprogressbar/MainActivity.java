package com.sen.circleprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleProgressBar cp1 = (CircleProgressBar) findViewById(R.id.cp1);
        CircleProgressBar cp2 = (CircleProgressBar) findViewById(R.id.cp2);
        CircleProgressBar cp3 = (CircleProgressBar) findViewById(R.id.cp3);
        cp1.setProgress(80);
        cp2.setProgress(60);
        cp3.setProgress(70);
    }
}
