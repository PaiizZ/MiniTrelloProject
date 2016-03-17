package com.minitrello.minitrello.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import models.Storage;

public class RenameCardActivity extends Activity {

    private Button confirmBtn;
    private TextView CardTitle;
    private int listcard_index;
    private int card_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_card);
        card_index = (int)getIntent().getSerializableExtra("card_index");
        listcard_index = (int)getIntent().getSerializableExtra("listcards_index");
        initComponent();
    }

    private void initComponent(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        confirmBtn = (Button)findViewById(R.id.confirm_rename_card_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CardTitle.getText().toString().equals("")) {
                    Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).setTitle(CardTitle.getText().toString());
                }
                finish();
            }
        });

        CardTitle = (TextView) findViewById(R.id.card_title_rename);
    }
}
