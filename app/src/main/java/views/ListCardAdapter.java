package views;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minitrello.minitrello.R;

import java.util.List;
import java.util.Random;

import models.ListCard;

/**
 * Created by พศิน on 28/2/2559.
 */
public class ListCardAdapter extends RecyclerView.Adapter<ListCardViewHolder> {

    private List<ListCard> listCards;

    public ListCardAdapter(List<ListCard> objects) {
        listCards = objects;
    }

    @Override
    public ListCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.listcard_cell, parent, false);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        v.setBackgroundColor(color);
        return new ListCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListCardViewHolder holder, int position) {
        holder.listcardview.setText(listCards.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listCards.size();
    }
}
