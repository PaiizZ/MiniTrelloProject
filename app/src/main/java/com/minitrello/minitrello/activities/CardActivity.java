package com.minitrello.minitrello.activities;

import android.content.Intent;
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

    private Button saveDescription;
    private Button deleteCardBtn;
    private Button renameCardBtn;
    private EditText descriptionEditText;
    private int listcard_index;
    private int card_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        card_index = (int)getIntent().getSerializableExtra("card_index");
        listcard_index = (int)getIntent().getSerializableExtra("listcards_index");
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
                loadDescription();
            }
        });

        deleteCardBtn = (Button) findViewById(R.id.delete_card_btn);
        deleteCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCard();
                finish();
            }
        });

        renameCardBtn = (Button)findViewById(R.id.rename_card_button);
        renameCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, RenameCardActivity.class);
                intent.putExtra("card_index", card_index);
                intent.putExtra("listcards_index", listcard_index);
                startActivity(intent);
            }
        });

    }

    private void deleteCard(){
        Storage.getInstance().loadListCard().get(listcard_index).deleteCard(card_index);
    }

    private void saveDescription(){
        Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).setDescription(descriptionEditText.getText().toString());
    }

    private void loadDescription(){
        descriptionEditText.setText(Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).getDescription());
    }

}
