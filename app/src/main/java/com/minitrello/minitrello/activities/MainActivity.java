package com.minitrello.minitrello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import views.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listcardRecyclerView;
    private List<ListCard> listcards;
    private ListCardAdapter listCardAdapter;
    private AlertDialog.Builder clearDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent(){
        clearDialog = new AlertDialog.Builder(this);
        listcards = new ArrayList<ListCard>();
        listCardAdapter = new ListCardAdapter(listcards);
        listcardRecyclerView = (RecyclerView) findViewById(R.id.listcard_Recycler);
        listcardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listcardRecyclerView.setAdapter(listCardAdapter);
        listcardRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ListCardActivity.class);
                intent.putExtra("listcard_index", position);
                startActivity(intent);
            }
        }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateMenu.class));
            }
        });

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

    private void loadListCard(){
        listcards.clear();
        for(ListCard lcard: Storage.getInstance().loadListCard()){
            listcards.add(lcard);
        }

        listCardAdapter.notifyDataSetChanged();
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
        Storage.getInstance().clearListCard();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadListCard();
    }
}
