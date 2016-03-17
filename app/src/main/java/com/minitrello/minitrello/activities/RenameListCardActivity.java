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

public class RenameListCardActivity extends Activity {

    private Button confirmBtn;
    private TextView ListCardTitle;
    private int listcard_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_list_card);
        listcard_index = (int)getIntent().getSerializableExtra("listcard_index");
        initComponent();
    }

    private void initComponent(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        confirmBtn = (Button)findViewById(R.id.confirm_rename_listcard_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ListCardTitle.getText().toString().equals("")) {
                    Storage.getInstance().loadListCard().get(listcard_index).setTitle(ListCardTitle.getText().toString());
                }
                finish();
            }
        });

        ListCardTitle = (TextView) findViewById(R.id.listcard_title_rename);
    }
}
