package com.minitrello.minitrello.activities;

import android.content.Intent;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import java.util.ArrayList;
import java.util.List;

import models.Card;
import models.ListCard;
import models.Storage;
import views.CardAdapter;

public class ListCardActivity extends AppCompatActivity {

    private Button createCardBtn;
    private TextView cardTitle;
    private ListCard listCard;
    private ListView cardListView;
    private List<Card> cards;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
        listCard = (ListCard)getIntent().getSerializableExtra("listcard");
        initComponent();
    }

    private void initComponent(){
        cards = new ArrayList<Card>();
        cardAdapter = new CardAdapter(this, R.layout.card_cell,cards);
        cardListView = (ListView) findViewById(R.id.card_list_view);
        cardListView.setAdapter(cardAdapter);
        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListCardActivity.this, CardActivity.class);
                intent.putExtra("card", cards.get(position));
                startActivity(intent);
            }
        });

        cardTitle = (TextView)findViewById(R.id.card_title);
        createCardBtn = (Button)findViewById(R.id.create_card_btn);
        createCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardTitle.getText().toString().equals("")) {
                    saveNewCard();
                    onPostResume();
                }
                cardTitle.setText("");
            }
        });


    }

    private void loadCard(){
        cards.clear();
        for(Card card: listCard.loadCards()){
            cards.add(card);
        }
        cardAdapter.notifyDataSetChanged();
    }

    private void  saveNewCard(){
        Storage.getInstance().saveCard(listCard, new Card(cardTitle.getText().toString()));
        listCard.saveCard(new Card(cardTitle.getText().toString()));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadCard();
    }
}
