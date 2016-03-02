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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import models.ListCard;

/**
 * Created by พศิน on 28/2/2559.
 */
public class ListCardAdapter extends RecyclerView.Adapter<ListcardViewHolder> {

    private List<ListCard> listCards;

    public ListCardAdapter(List<ListCard> objects) {
        listCards = objects;
    }

    @Override
    public ListcardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.listcard_cell, parent, false);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        v.setBackgroundColor(color);
        return new ListcardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListcardViewHolder holder, int position) {
        holder.listcardview.setText(listCards.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listCards.size();
    }
}
