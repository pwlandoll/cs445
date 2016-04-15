package edu.jcu.kirsch.databaseexample1;

/**
 * Created by Marc on 4/14/2016.
 */
public class OneComment {
    // This is our model and contains the data we will save to the database and show in the user interface.
    private long id;
    private String comment;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return comment;
    }
}

