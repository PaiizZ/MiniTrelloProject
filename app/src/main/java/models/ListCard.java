package models;

import java.io.Serializable;

/**
 * Created by พศิน on 27/2/2559.
 */
public class ListCard implements Serializable {
    private String title;

    public ListCard(String title){
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
