package kirsch.jcu.edu.swarmimagesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout gallery;
    private Inventory inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery = (LinearLayout)findViewById(R.id.linearList);
        inventory = new Inventory();
        fillSwarmGallery();
    }

    public void fillSwarmGallery() {
        ImageButton swarmButton;
        ArrayList<Entry> images = inventory.getInventory();
        for(Entry entry : images) {
            // Add to gallery
            swarmButton = new ImageButton(this);
            swarmButton.setContentDescription(entry.getDes());
            swarmButton.setImageDrawable(getResources().getDrawable(entry.getId()));
            swarmButton.setOnClickListener(displayInformation);
            gallery.addView(swarmButton);
        }
    }

    private View.OnClickListener displayInformation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getBaseContext(), view.getContentDescription(), Toast.LENGTH_LONG).show();
        }
    };
}
