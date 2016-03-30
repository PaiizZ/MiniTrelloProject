package com.minitrello.minitrello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.minitrello.minitrello.R;

import java.util.ArrayList;
import java.util.List;

import models.Card;
import models.Comment;
import models.ListCard;
import models.Storage;
import views.CommentAdapter;

public class CardActivity extends AppCompatActivity {

    private Button saveDescription;
    private Button deleteCardBtn;
    private Button renameCardBtn;
    private Button addCommentBtn;
    private ListView comment_listview;
    private EditText descriptionEditText;
    private EditText commentEditText;
    private CommentAdapter commentAdapter;
    private List<Comment> comments;
    private int listcard_index;
    private AlertDialog.Builder commentDialog;
    private AlertDialog.Builder deleteDialog;
    private int card_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        card_index = (int)getIntent().getSerializableExtra("card_index");
        listcard_index = (int)getIntent().getSerializableExtra("listcards_index");
        initComponent();
    }

    private void setCommentDialog() {
        commentDialog.setTitle("Confirm message");
        commentDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addComment();
                commentEditText.getText().clear();
                loadComment();
            }
        });
        commentDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog comment = commentDialog.create();
        comment.show();

    }

    private void setDelteDialog() {
        deleteDialog.setTitle("Confirm message");
        deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCard();
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


    private void initComponent(){
        commentDialog = new AlertDialog.Builder(this);
        deleteDialog = new AlertDialog.Builder(this);
        saveDescription = (Button)findViewById(R.id.save_description_btn);
        descriptionEditText = (EditText) findViewById(R.id.card_description_editText);
        loadDescription();
        saveDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDescription();
                loadDescription();
                Toast.makeText(getApplicationContext(), "Description has been saved.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        deleteCardBtn = (Button) findViewById(R.id.delete_card_btn);
        deleteCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setDelteDialog();
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
        comments = new ArrayList<Comment>();
        comment_listview = (ListView)findViewById(R.id.comment_listView);
        commentAdapter = new CommentAdapter(this,R.layout.comment_cell,comments);
        comment_listview.setAdapter(commentAdapter);
        commentEditText = (EditText)findViewById(R.id.comment_editText);
        addCommentBtn = (Button)findViewById(R.id.add_comment_btn);
        loadComment();
        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEditText.getText().toString().equals("")) {
                    setCommentDialog();

                }
            }
        });

        comment_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        comment_listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = comment_listview.getCheckedItemCount();
                mode.setTitle(checkedCount + " Selected");
                    commentAdapter.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.main,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
               switch(item.getItemId()){
                   case R.id.delete:
                       SparseBooleanArray selected = commentAdapter.getSelectId();
                       for(int i=(selected.size()-1); i>=0 ;i--) {
                           if(selected.valueAt(i)) {
                               Comment selecteditem = commentAdapter.getItem(selected.keyAt(i));
                               Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).deleteComment(selected.keyAt(i));
                               commentAdapter.remove(selecteditem);
                           }
                       }
                       mode.finish();
                       return true;
                   default:
                       return false;

               }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                commentAdapter.removeSelection();
            }
        });

        /*
        comment_listview.setLongClickable(true);
        comment_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });*/
    }

    private void loadComment(){
        comments.clear();
        for(Comment cm: Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).getListComments()){
            comments.add(cm);
        }
        commentAdapter.notifyDataSetChanged();
    }

    private void addComment(){
        Storage.getInstance().loadListCard().get(listcard_index).loadCards().get(card_index).addComment(new Comment(commentEditText.getText().toString()));
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
