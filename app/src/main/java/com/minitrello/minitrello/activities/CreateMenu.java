package com.minitrello.minitrello.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.minitrello.minitrello.R;

/**
 * Created by พศิน on 27/2/2559.
 */
public class CreateMenu extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.createwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
