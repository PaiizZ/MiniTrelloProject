package models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by พศิน on 27/2/2559.
 */
public class Storage {

    private List<ListCard> savedListCards;

    private static Storage instance;

    private Storage(){
        savedListCards = new ArrayList<ListCard>();
    }

    public static Storage getInstance(){
        if(instance==null)instance = new Storage();
        return  instance;
    }

    public void saveListCard(ListCard listCard){
        savedListCards.add(listCard);
    }

    public void saveCard(ListCard listCard,Card card){
        getListCard(listCard).saveCard(card);
    }

    public ListCard getListCard(ListCard listCard){
        for(int i=0;i< savedListCards.size();i++) {
            if (savedListCards.get(i).equals(listCard)) {
                return savedListCards.get(i);
            }
        }
        return null;
    }

    public Card getCard(ListCard listcard,Card card){
          return (Card)getListCard(listcard).loadCard(card);
    }

    public List<ListCard> loadListCard(){
        return savedListCards;
    }

}
