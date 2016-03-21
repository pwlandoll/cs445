package kirsch.jcu.edu.intentexample2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GetImageActivity extends AppCompatActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
         Intent thisIntent = this.getIntent();
         //Bundle is a data structure that can hold various types of data. In particular, we get
         //the strings we stored using putExtra.
         Bundle thisBundle = thisIntent.getExtras();
         String option = thisBundle.getString("kirsch.jcu.edu.IntentExampleRandom.option");
         if (option.equals("random"))
         {
             //User wants us to randomly select an image
             //Here is the code to simulate our choice of image. 40% clock, 40% launcher, and 20% failure
             double pick = Math.random();
             String image="";
             int result;
             if (pick<.4)
             {
                 image = "clock";
                 result = RESULT_OK;
             }
             else if(pick < .8)
             {
                 image = "launcher";
                 result = RESULT_OK;
             }
             else
             {
               image = "error";
                 result = RESULT_CANCELED;
             }
             this.getIntent().putExtra("kirsch.jcu.edu.IntentExample2.ImageName",image);
             setResult(result,this.getIntent());
             finish();
         }
    }

    public void getImage(View view)
    {
        String imageName;
        if(view==findViewById(R.id.clockImageButton))
        {
            imageName = "clock";
        }
        else
        {
            imageName = "launcher";
        }
        //Send the imageName back to the main activity
        //Use the current Intent and add to it the imageName
        this.getIntent().putExtra("kirsch.jcu.edu.IntentExample2.ImageName",imageName);
        setResult(RESULT_OK,this.getIntent());
        finish();
    }
}

