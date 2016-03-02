package views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.minitrello.minitrello.R;

/**
 * Created by พศิน on 2/3/2559.
 */
public class CardViewHolder extends   RecyclerView.ViewHolder {
        TextView cardview;

    CardViewHolder(View itemView) {
        super(itemView);
        cardview = (TextView) itemView.findViewById(R.id.card);
    }
}
