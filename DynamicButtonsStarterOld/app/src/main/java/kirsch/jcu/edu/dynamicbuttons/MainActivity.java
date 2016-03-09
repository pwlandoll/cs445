package kirsch.jcu.edu.dynamicbuttons;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private Integer rows, columns;
    private EditText numberOfRows, numberOfColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect resources to instance variables
        numberOfRows = (EditText)findViewById(R.id.rowEditText);
        numberOfColumns = (EditText)findViewById(R.id.colEditText);
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
        int padding = 5; // padding in px
        Integer screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenWidth -= (int)(2*getResources().getDimension(R.dimen.activity_horizontal_margin));
        Integer buttonWidth = screenWidth / columns - 2 * padding;
        TableLayout holder = (TableLayout)findViewById(R.id.buttonHolderTableLayout);
        holder.removeAllViews(); // to start fresh every time a button is created
        TableRow.LayoutParams buttonParameters = new TableRow.LayoutParams(buttonWidth, buttonWidth);
        buttonParameters.setMargins(padding, padding, padding, padding);

        // Create Buttons
        for (int row = 0; row < rows; row++) {
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            newRow.setBackgroundColor(getResources().getColor(R.color.white));
            // Create columns buttons for this row
            for (int col = 0; col < columns; col++) {
                Button addButton = new Button(this);
                addButton.setOnClickListener(buttonListener);
                addButton.setTextSize(10);
                addButton.setBackgroundColor(getResources().getColor(R.color.background));
                addButton.setTextColor(getResources().getColor(R.color.white));
                addButton.setLayoutParams(buttonParameters);
                newRow.addView(addButton);
                buttons[row][col] = addButton;
            }
            holder.addView(newRow);
        }
    }

    private Button.OnClickListener buttonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button picked = (Button)view;
            int theRow = 0, theCol = 0;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    if (picked.equals(buttons[row][col])) {
                        theRow = row;
                        theCol = col;
                    }
                }
            }
            picked.setTextColor(getResources().getColor(R.color.white));
            picked.setBackgroundColor(getResources().getColor(R.color.blue));
            String message = getResources().getText(R.string.Hit) + "(" + theRow + "," + theCol + ")";
            picked.setText(message);
        }
    };

}
