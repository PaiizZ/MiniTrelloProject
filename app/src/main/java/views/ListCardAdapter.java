package views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import java.util.List;

import models.ListCard;

/**
 * Created by พศิน on 28/2/2559.
 */
public class ListCardAdapter extends ArrayAdapter<ListCard> {

    public ListCardAdapter(Context context, int resource, List<ListCard> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listcard_cell, null);
        }
        TextView card = (TextView)v.findViewById(R.id.listcard);

        ListCard listCard = getItem(position);
        card.setText(listCard.getTitle());

        return v;
    }
}
