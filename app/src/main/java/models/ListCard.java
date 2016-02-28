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
    private long currentTime;

    public ListCard(String title){
        this.title = title;
        this.cards = new ArrayList<Card>();
        currentTime = System.currentTimeMillis();
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
    public boolean equals(Object o) {
        ListCard other = (ListCard) o;
        return (this.toString().equals(other.toString()))&&(this.currentTime == other.currentTime);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
