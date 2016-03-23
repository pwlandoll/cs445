package kirsch.jcu.edu.intentexample2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton myImage;
    private Button startButton, stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageButton)findViewById(R.id.displayImageButton);
        startButton = (Button)findViewById(R.id.startPlayerButton);
        stopButton = (Button)findViewById(R.id.stopPlayerButton);
    }

    public void onStartClick(View view)
    {
        startService(new Intent(getBaseContext(), MediaPlayerService.class));
    }

    public void onStopClick(View view)
    {
        stopService(new Intent(getBaseContext(), MediaPlayerService.class));
    }

    public void onClick(View view)
    {
        Intent getImageIntent = new Intent(view.getContext(),GetImageActivity.class);
        getImageIntent.putExtra("kirsch.jcu.edu.IntentExampleRandom.option","select");
        startActivityForResult(getImageIntent, 100);
    }

    public void getRandomImage(View view)
    {
        Intent getImageIntent = new Intent(view.getContext(),GetImageActivity.class);
        getImageIntent.putExtra("kirsch.jcu.edu.IntentExampleRandom.option","random");
        startActivityForResult(getImageIntent, 100);
    }

    //Handle the return of the Intent from GetImageActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==100 && resultCode==RESULT_OK)
        {
            //Get the name of the image from data and set the imageButton appropriately
            String name = data.getStringExtra("kirsch.jcu.edu.IntentExample2.ImageName");
            if(name.equals("clock"))
            {
                myImage.setBackground(getResources().getDrawable(R.drawable.clockface));
            }
            else
            {
                myImage.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
            }
            Toast.makeText(getBaseContext(),getResources().getString(R.string.success).toString(),Toast.LENGTH_LONG).show();
        }
        else if (resultCode==RESULT_CANCELED)
        {
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.error).toString(),Toast.LENGTH_LONG).show();
            myImage.setBackground(null);
        }
        else
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.unknown).toString(),Toast.LENGTH_LONG).show();
            myImage.setBackground(null);
        }
    }
}

