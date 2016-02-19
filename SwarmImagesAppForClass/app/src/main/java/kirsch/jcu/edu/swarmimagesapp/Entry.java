package kirsch.jcu.edu.swarmimagesapp;

/**
 * Created by peter on 2/19/16.
 */
public class Entry {
    private String des;
    private int id;

    public Entry(String des, int id) {
        this.des = des;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
