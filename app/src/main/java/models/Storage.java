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

    public void clearListCard(){
        savedListCards.clear();
    }

    public List<ListCard> loadListCard(){
        return savedListCards;
    }

}
