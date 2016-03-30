package models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by พศิน on 27/2/2559.
 */
public class Card implements Serializable {
    private String title;
    private String description;
    private List<Comment> listComments;
    private long currentTime;

    public Card(String title){
        this.title = title;
        currentTime = System.currentTimeMillis();
        listComments = new ArrayList<Comment>();
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

    public void addComment(Comment m){
        listComments.add(m);
    }

    public List<Comment> getListComments() {
        return listComments;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
