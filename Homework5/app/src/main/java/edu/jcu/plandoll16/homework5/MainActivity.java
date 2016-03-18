package edu.jcu.plandoll16.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private EditText numberOfRows, numberOfColumns;
    private int padding;
    private Integer rows, columns, screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfRows = (EditText)findViewById(R.id.rowEditText);
        numberOfColumns = (EditText)findViewById(R.id.colEditText);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth -= (int)(2*getResources().getDimension(R.dimen.activity_horizontal_margin));
        screenHeight -= (int)(2*getResources().getDimension(R.dimen.activity_vertical_margin));
        padding = 5;
    }

    public void initialize(View view) {
        try {
            rows = Integer.parseInt(numberOfRows.getText().toString());
            columns = Integer.parseInt(numberOfColumns.getText().toString());
        } catch (Exception e) {
            // If there's an error, default to 2
            rows = 2;
            columns = 2;
            numberOfRows.setText(rows.toString());
            numberOfColumns.setText(columns.toString());
        }
        buttons = new Button[rows][columns];
        Integer buttonSize = Math.min(screenHeight, screenWidth);
        TableLayout holder = (TableLayout)findViewById(R.id.buttonHolderTableLayout);
        holder.removeAllViews();
        TableRow.LayoutParams buttonParameters = new TableRow.LayoutParams(buttonSize, buttonSize);
        buttonParameters.setMargins(padding, padding, padding, padding);

        for (int row = 0; row < rows; row++) {
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            newRow.setBackgroundColor(getResources().getColor(R.color.white));
            double type;
            //String name;
            // Create columns buttons for this row
            for (int col = 0; col < columns; col++) {
                //Button addButton = new Button(this);
                //name = "(" + row + ", " + col + ")";
                type = Math.random();
                Button addButton;
                if (type < 0.5) {
                    addButton = new RedBlueButton(this);
                } else {
                    addButton = new YellowGreenButton(this);
                }
                addButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button picked = (Button)view;
                    }
                });
                addButton.setTextSize(10);
                //addButton.setBackgroundColor(getResources().getColor(R.color.background));
                addButton.setTextColor(getResources().getColor(R.color.white));
                addButton.setLayoutParams(buttonParameters);
                newRow.addView(addButton);
                buttons[row][col] = addButton;
            }
            holder.addView(newRow);
        }

    }
}
