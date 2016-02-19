package kirsch.jcu.edu.swarmimagesapp;

import java.util.ArrayList;

/**
 * Created by peter on 2/19/16.
 * Holds an ArrayList of Entries
 *  Init Entries, hard coded as to take the place of a database query
 */
public class Inventory {
    private ArrayList<Entry> images;

    public Inventory() {
        images = new ArrayList<Entry>();
        images.add(new Entry("Birds Flocking", R.drawable.birds));
        images.add(new Entry("Human Swarming", R.drawable.human_swarm));
        images.add(new Entry("Real Robots", R.drawable.real_robots));
        images.add(new Entry("Soduku Image 1", R.drawable.soduku));
        images.add(new Entry("Soduku Image 2", R.drawable.soduku2));
        images.add(new Entry("Swarm Day Event", R.drawable.swarm_day));
        images.add(new Entry("Stock Market Swarm", R.drawable.stock_market_swarm));
    }

    public ArrayList<Entry> getInventory() {
        return images;
    }
}
