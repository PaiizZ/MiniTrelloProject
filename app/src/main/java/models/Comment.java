package models;

/**
 * Created by พศิน on 30/3/2559.
 */
public class Comment {
    private String detail;

    public Comment(String str){
        detail = str;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
