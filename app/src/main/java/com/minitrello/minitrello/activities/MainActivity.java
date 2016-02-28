package com.minitrello.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.minitrello.minitrello.R;

import java.util.ArrayList;
import java.util.List;

import models.Card;
import models.ListCard;
import models.Storage;
import views.ListCardAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listcardListView;
    private List<ListCard> listcards;
    private ListCardAdapter listCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent(){
        listcards = new ArrayList<ListCard>();
        listCardAdapter = new ListCardAdapter(this, R.layout.note_cell,listcards);
        listcards.add(new ListCard("a"));
        listcardListView = (ListView) findViewById(R.id.listcard_list_view);
        listcardListView.setAdapter(listCardAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, CreateMenu.class));
            }
        });

    }

    private void loadListCard(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //listcards.clear();
        for(ListCard lcard: Storage.getInstance().loadListCard()){
            listcards.add(lcard);
        }
        listCardAdapter.notifyDataSetChanged();
    }
}
