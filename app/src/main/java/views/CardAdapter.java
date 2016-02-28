package views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import java.util.List;

import models.Card;
import models.ListCard;

/**
 * Created by พศิน on 28/2/2559.
 */
public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.card_cell, null);
        }
        TextView cardView = (TextView)v.findViewById(R.id.card);

        Card card = getItem(position);
        cardView.setText(card.getTitle());

        return v;
    }
}
