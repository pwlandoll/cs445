package edu.jcu.plandoll16.homework5;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

/**
 * Created by peter on 3/17/16.
 */
public class RedBlueButton extends Button {

    public RedBlueButton(Context context) {
        super(context);
    }

    public void switchColor() {
        ColorDrawable bgColorDrawable = (ColorDrawable)this.getBackground();
        int color = bgColorDrawable.getColor();
        if (color == getResources().getColor(R.color.blue)) {
            this.setBackgroundColor(getResources().getColor(R.color.red));
        } else if (color == getResources().getColor(R.color.red)) {
            this.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            if (Math.random() < 0.5) {
                this.setBackgroundColor(getResources().getColor(R.color.red));
            } else {
                this.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        }
    }
}
