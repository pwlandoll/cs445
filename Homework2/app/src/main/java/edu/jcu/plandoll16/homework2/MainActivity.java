package edu.jcu.plandoll16.homework2;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // 2D array for the 25 buttons, and an integer for the current color
    private Button[][] buttons;
    private int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Default current color to red
        currentColor = getResources().getColor(R.color.red);
        // Put each button in the array
        buttons = new Button[][] {
                {
                        (Button)findViewById(R.id.button00),
                        (Button)findViewById(R.id.button01),
                        (Button)findViewById(R.id.button02),
                        (Button)findViewById(R.id.button03),
                        (Button)findViewById(R.id.button04)
                },
                {
                        (Button)findViewById(R.id.button10),
                        (Button)findViewById(R.id.button11),
                        (Button)findViewById(R.id.button12),
                        (Button)findViewById(R.id.button13),
                        (Button)findViewById(R.id.button14)
                },
                {
                        (Button)findViewById(R.id.button20),
                        (Button)findViewById(R.id.button21),
                        (Button)findViewById(R.id.button22),
                        (Button)findViewById(R.id.button23),
                        (Button)findViewById(R.id.button24)
                },
                {
                        (Button)findViewById(R.id.button30),
                        (Button)findViewById(R.id.button31),
                        (Button)findViewById(R.id.button32),
                        (Button)findViewById(R.id.button33),
                        (Button)findViewById(R.id.button34)
                },
                {
                        (Button)findViewById(R.id.button40),
                        (Button)findViewById(R.id.button41),
                        (Button)findViewById(R.id.button42),
                        (Button)findViewById(R.id.button43),
                        (Button)findViewById(R.id.button44)
                }
        }; // End of buttons
    }

    public void changeColor(View view) {
        currentColor = ((ColorDrawable)view.getBackground()).getColor();
    }

    // Iterate through the array, setting each button to the current color
    public void paintAll(View view) {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setBackgroundColor(currentColor);
            }
        }
    }

    // Public method for actually connecting buttons to paintButton
    public void paintButton(View view) {
        paintColor((Button)findViewById(view.getId()));
    }

    // Method for making coloring buttons easier
    private void paintColor(Button b) {
        b.setBackgroundColor(currentColor);
    }

    // Paint an O shape on the buttons
    public void paintO(View view) {
        // Reset the colors to grey first, then paint the O
        reset(view);
        int[][] oPattern = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 4}, {2, 0},
                {2, 4}, {3, 0}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};
        for (int[] i : oPattern) {
            paintColor(buttons[i[0]][i[1]]);
        }
    }

    // Paint an X shape on the buttons
    public void paintX(View view) {
        // Reset the colors to grey first, then paint the X
        reset(view);
        int[][] xPattern = {{0, 0}, {0, 4}, {1, 1}, {1, 3}, {2, 2}, {3, 1}, {3, 3}, {4, 0}, {4, 4}};
        for (int[] i : xPattern) {
            paintColor(buttons[i[0]][i[1]]);
        }
    }

    // Paint all the buttons grey
    public void reset(View view) {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }
}
