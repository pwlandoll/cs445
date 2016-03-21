package edu.jcu.plandoll16.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText numberOfRows, numberOfColumns;
    private int padding;
    private Integer screenWidth, screenHeight;
    private SpecialButton[][] buttons;
    private TextView bottomView;

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
        Integer rows, columns;
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
        Integer buttonSize = (Math.min(screenHeight, screenWidth)) / (rows + 1)- 2 * padding;
        TableLayout holder = (TableLayout)findViewById(R.id.buttonHolderTableLayout);
        holder.removeAllViews();
        TableRow.LayoutParams buttonParameters = new TableRow.LayoutParams(buttonSize, buttonSize);
        buttonParameters.setMargins(padding, padding, padding, padding);
        buttons = new SpecialButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            newRow.setBackgroundColor(getResources().getColor(R.color.white));
            // Create columns buttons for this row
            for (int col = 0; col < columns; col++) {
                SpecialButton addButton;
                if (Math.random() < 0.5) {
                    addButton = new RedBlueButton(this, buttonSize);
                } else {
                    addButton = new YellowGreenButton(this, buttonSize);
                }
                addButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpecialButton picked = (SpecialButton)view;
                        picked.switchColor();
                    }
                });
                addButton.setBackgroundColor(getResources().getColor(R.color.grey));
                addButton.setLayoutParams(buttonParameters);
                newRow.addView(addButton);
            }
            holder.addView(newRow);
        }
        TableRow bottomRow = new TableRow(this);
        bottomRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        bottomRow.setBackgroundColor(getResources().getColor(R.color.white));
        TextView bottomTextView = new TextView(this);
        bottomTextView.setBackgroundColor(getResources().getColor(R.color.grey));
        bottomTextView.setHeight(buttonSize);
        bottomTextView.setWidth(buttonSize);
        bottomTextView.setId(R.id.bottomView);
        bottomRow.addView(bottomTextView);
        holder.addView(bottomRow);
    }
}
