package views;

import android.content.Context;
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

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
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
}
