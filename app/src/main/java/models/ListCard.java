package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by พศิน on 27/2/2559.
 */
public class ListCard implements Serializable {
    private String title;
    private List<Card> cards;

    public ListCard(String title){
        this.title = title;
        this.cards = new ArrayList<Card>();
    }

    public String getTitle() {
        return title;
    }

    public List<Card> loadCards(){
        return cards;
    }

    public void saveCard(Card card){
        cards.add(card);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
