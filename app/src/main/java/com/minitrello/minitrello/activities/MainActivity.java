package com.minitrello.minitrello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minitrello.minitrello.R;

import java.util.ArrayList;
import java.util.List;

import models.ListCard;
import models.Storage;
import views.ListCardAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listcardListView;
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
        listCardAdapter = new ListCardAdapter(listcards);
        listcardListView = (RecyclerView) findViewById(R.id.listcard_Recycler);
        listcardListView.setLayoutManager(new LinearLayoutManager(this));
        listcardListView.setAdapter(listCardAdapter);

        
        /*
        listcardListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ListCardActivity.class);
                intent.putExtra("listcard",listcards.get(position));
                startActivity(intent);
            }
        });
        */

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
        listcards.clear();
        for(ListCard lcard: Storage.getInstance().loadListCard()){
            listcards.add(lcard);
        }

        listCardAdapter.notifyDataSetChanged();
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
    protected void onPostResume() {
        super.onPostResume();
        loadListCard();
    }
}
