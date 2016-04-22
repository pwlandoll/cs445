package edu.jcu.kirsch.databaseexample1;

/**
 * Created by Marc on 4/14/2016.
 */
public class OneRecord {
    // This is our model and contains the data we will save to the database and show in the user interface.
    private long id;
    private String name,email;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString()
    {
        return "id: "+ id + "  name: "+name+"  email: " + email;
    }
}

