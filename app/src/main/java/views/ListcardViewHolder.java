package views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.minitrello.minitrello.R;

/**
 * Created by พศิน on 1/3/2559.
 */
public class ListcardViewHolder extends RecyclerView.ViewHolder{
    TextView listcardview;

    ListcardViewHolder(View itemView) {
        super(itemView);
        listcardview = (TextView) itemView.findViewById(R.id.listcard);
    }
}
