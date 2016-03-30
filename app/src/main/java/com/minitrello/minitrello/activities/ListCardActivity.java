package com.minitrello.minitrello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import views.RecyclerItemClickListener;

public class ListCardActivity extends AppCompatActivity {

    private Button createCardBtn;
    private Button deleteListCardBtn;
    private Button renameListCardBtn;
    private TextView cardTitle;
    private TextView ListcardTitle;
    private RecyclerView cardListRecyclerView;
    private List<Card> cards;
    private CardAdapter cardAdapter;
    private AlertDialog.Builder deleteDialog;
    private AlertDialog.Builder clearDialog;
    private int listcard_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
        listcard_index = (int)getIntent().getSerializableExtra("listcard_index");
        initComponent();
    }

    private void initComponent(){
        deleteDialog = new AlertDialog.Builder(this);
        clearDialog = new AlertDialog.Builder(this);
        ListcardTitle = (TextView)findViewById(R.id.listcard_title_head);
        ListcardTitle.setText(Storage.getInstance().loadListCard().get(listcard_index).toString());
        cards = new ArrayList<Card>();
        cardAdapter = new CardAdapter(cards);
        cardListRecyclerView = (RecyclerView) findViewById(R.id.card_Recycler);
        cardListRecyclerView.setAdapter(cardAdapter);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ListCardActivity.this, CardActivity.class);
                intent.putExtra("card_index", position);
                intent.putExtra("listcards_index", listcard_index);
                startActivity(intent);
            }
        }));

        cardTitle = (TextView)findViewById(R.id.card_title);
        createCardBtn = (Button)findViewById(R.id.create_card_btn);
        createCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cardTitle.getText().toString().equals("")) {
                    saveNewCard();
                    onPostResume();
                }
                cardTitle.setText("");
            }
        });

        deleteListCardBtn = (Button)findViewById(R.id.delete_listcard_btn);
        deleteListCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDelteDialog();
            }
        });

        renameListCardBtn = (Button)findViewById(R.id.rename_listcard_button);
        renameListCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCardActivity.this, RenameListCardActivity.class);
                intent.putExtra("listcard_index", listcard_index);
                startActivity(intent);
            }
        });

    }

    private void setDelteDialog() {
        deleteDialog.setTitle("Confirm message");
        deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteListCard();
                finish();
            }
        });
        deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog delete = deleteDialog.create();
        delete.show();

    }

    private void setClearDialog() {
        clearDialog.setTitle("Confirm message");
        clearDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear();
                onPostResume();
            }
        });
        clearDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog clear = clearDialog.create();
        clear.show();

    }

    private void deleteListCard(){
        Storage.getInstance().loadListCard().remove(listcard_index);
    }

    private void loadCard(){
        cards.clear();
        for(Card card: Storage.getInstance().loadListCard().get(listcard_index).loadCards()){
            cards.add(card);
        }
        cardAdapter.notifyDataSetChanged();
    }

    private void  saveNewCard(){
        Storage.getInstance().loadListCard().get(listcard_index).saveCard(new Card(cardTitle.getText().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            setClearDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clear(){
        Storage.getInstance().loadListCard().get(listcard_index).clearCard();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadCard();
        ListcardTitle.setText(Storage.getInstance().loadListCard().get(listcard_index).toString());
    }
}
