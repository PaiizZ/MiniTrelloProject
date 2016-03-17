package models;


import java.io.Serializable;

/**
 * Created by พศิน on 27/2/2559.
 */
public class Card implements Serializable {
    private String title;
    private String description;
    private long currentTime;

    public Card(String title){
        this.title = title;
        currentTime = System.currentTimeMillis();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        Card other = (Card) o;
        return (this.toString().equals(other.toString()))&&(this.currentTime == other.currentTime);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
