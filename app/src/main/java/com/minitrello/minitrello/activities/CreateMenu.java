package com.minitrello.minitrello.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import org.w3c.dom.Text;

import models.ListCard;
import models.Storage;

/**
 * Created by พศิน on 27/2/2559.
 */
public class CreateMenu extends Activity{

    private Button confirmBtn;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createwindow);
        initComponent();

    }

    private void initComponent(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        confirmBtn = (Button)findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewListCard();
                
                finish();
            }
        });

        title = (TextView) findViewById(R.id.listcard_title);
    }

    private void saveNewListCard(){
        Storage.getInstance().saveListCard(new ListCard(title.getText().toString()));
    }

}
