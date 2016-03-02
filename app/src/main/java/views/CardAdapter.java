package views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minitrello.minitrello.R;

import java.util.List;
import java.util.Random;

import models.Card;
import models.ListCard;

/**
 * Created by พศิน on 28/2/2559.
 */
public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private List<Card> cards;

    public CardAdapter(List<Card> objects) {
        cards = objects;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.card_cell, parent, false);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        v.setBackgroundColor(color);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.cardview.setText(cards.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
