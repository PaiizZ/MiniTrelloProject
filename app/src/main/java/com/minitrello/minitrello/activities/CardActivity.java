package com.minitrello.minitrello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import models.Card;
import models.ListCard;
import models.Storage;

public class CardActivity extends AppCompatActivity {

    Button saveDescription;
    EditText descriptionEditText;
    Card card;
    ListCard listCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        card = (Card)getIntent().getSerializableExtra("card");
        listCard = (ListCard)getIntent().getSerializableExtra("listcards");
        initComponent();
    }


    private void initComponent(){
        saveDescription = (Button)findViewById(R.id.save_description_btn);
        descriptionEditText = (EditText) findViewById(R.id.card_description_editText);
        loadDescription();
        saveDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDescription();
                descriptionEditText.setText(card.getDescription());
            }
        });
    }

    private void saveDescription(){
        card.setDescription(descriptionEditText.getText().toString());
        Storage.getInstance().getCard(Storage.getInstance().getListCard(listCard),card).setDescription(descriptionEditText.getText().toString());
    }

    private void loadDescription(){
        descriptionEditText.setText(Storage.getInstance().getCard(Storage.getInstance().getListCard(listCard),card).getDescription());
    }



}
