package kirsch.jcu.edu.intentexample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GetImageActivity extends AppCompatActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
    }

    public void getImage(View view) {
        String imageName;
        if (view == findViewById(R.id.clockImageButton)) {
            imageName = "clock";
        } else {
            imageName = "launcher";
        }
        this.getIntent().putExtra("pwlandoll.imageName", imageName);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
}

