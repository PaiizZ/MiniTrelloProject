package views;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import java.util.List;

import models.Comment;

/**
 * Created by พศิน on 30/3/2559.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {


    private List<Comment> commentlist;
    private SparseBooleanArray mSelectItemId;

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        mSelectItemId = new SparseBooleanArray();
        commentlist = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.comment_cell, null);
        }

        TextView comment = (TextView)v.findViewById(R.id.comment);

        Comment cm = getItem(position);
        comment.setText(cm.getDetail());

        return v;
    }

    public void remove (Comment cm){
        commentlist.remove(cm);
        notifyDataSetChanged();
    }

    public List<Comment> getCommentList(){
        return commentlist;
    }

    public void toggleSelection(int position){
        selectView(position, !mSelectItemId.get(position));
    }

    public void removeSelection(){
        mSelectItemId = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView (int position , boolean value){
        if(value){
            mSelectItemId.put(position,value);
        }
        else{
            mSelectItemId.delete(position);
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount(){
        return  mSelectItemId.size();
    }

    public SparseBooleanArray getSelectId(){
        return  mSelectItemId;
    }
}
