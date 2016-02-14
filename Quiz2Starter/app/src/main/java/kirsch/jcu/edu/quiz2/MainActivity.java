package kirsch.jcu.edu.quiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private Button row0Button, row2Button, colButton, hButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = new Button[4][4];
        buttons[0][0] = (Button)findViewById(R.id.button00);
        buttons[0][1] = (Button)findViewById(R.id.button01);
        buttons[0][2] = (Button)findViewById(R.id.button02);
        buttons[0][3] = (Button)findViewById(R.id.button03);
        buttons[1][0] = (Button)findViewById(R.id.button10);
        buttons[1][1] = (Button)findViewById(R.id.button11);
        buttons[1][2] = (Button)findViewById(R.id.button12);
        buttons[1][3] = (Button)findViewById(R.id.button13);
        buttons[2][0] = (Button)findViewById(R.id.button20);
        buttons[2][1] = (Button)findViewById(R.id.button21);
        buttons[2][2] = (Button)findViewById(R.id.button22);
        buttons[2][3] = (Button)findViewById(R.id.button23);
        buttons[3][0] = (Button)findViewById(R.id.button30);
        buttons[3][1] = (Button)findViewById(R.id.button31);
        buttons[3][2] = (Button)findViewById(R.id.button32);
        buttons[3][3] = (Button)findViewById(R.id.button33);

        row0Button = (Button)findViewById(R.id.row0Button);
        row2Button = (Button)findViewById(R.id.row2Button);
        colButton = (Button)findViewById(R.id.colButton);
        hButton = (Button)findViewById(R.id.hButton);
        resetButton = (Button)findViewById(R.id.resetButton);
    }

    public void reset(View view) {
        // Resets all button colors to blue
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        }
    }

    public void row0(View view) {
        // First reset all buttons to blue, then change 00, 01, 02, 03 to orange
        reset(view);
        buttons[0][0].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[0][1].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[0][2].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[0][3].setBackgroundColor(getResources().getColor(R.color.orange));
    }

    public void row2(View view) {
        // First reset all buttons to blue, then change 20, 21, 22, 23 to orange
        reset(view);
        buttons[2][0].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[2][1].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[2][2].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[2][3].setBackgroundColor(getResources().getColor(R.color.orange));
    }

    public void col(View view) {
        // First reset all buttons to blue, then change 01, 11, 21, 31 to orange
        reset(view);
        buttons[0][1].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[1][1].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[2][1].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[3][1].setBackgroundColor(getResources().getColor(R.color.orange));
    }

    public void h(View view) {
        // First call col, then fill in the rest of the h
        col(view); // This also calls reset
        buttons[2][2].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[0][3].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[1][3].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[2][3].setBackgroundColor(getResources().getColor(R.color.orange));
        buttons[3][3].setBackgroundColor(getResources().getColor(R.color.orange));
    }
}
