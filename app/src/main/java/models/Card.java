package models;


import java.io.Serializable;

/**
 * Created by พศิน on 27/2/2559.
 */
public class Card implements Serializable {
    private String title;
    private String description;

    public Card(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
