package kirsch.jcu.edu.intentexample2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = (ImageButton)findViewById(R.id.displayImageButton);
    }

    public void onClick(View view) {
        Intent getImageIntent = new Intent(view.getContext(), GetImageActivity.class);
        startActivityForResult(getImageIntent, 100);
    }

    // Handle the return of the Intent from GetImageActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = intent.getStringExtra("pwlandoll.imageName");
            if (name.equals("clock")) {
                imageButton.setBackground(getResources().getDrawable(R.drawable.clockface));
            } else {
                imageButton.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
            }
        }
    }

}

