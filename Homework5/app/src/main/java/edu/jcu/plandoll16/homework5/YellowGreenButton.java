package edu.jcu.plandoll16.homework5;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

/**
 * Created by peter on 3/17/16.
 */
public class YellowGreenButton extends Button {
    public YellowGreenButton(Context context) {
        super(context);
    }

    public int switchColor() {
        ColorDrawable bgColorDrawable = (ColorDrawable)this.getBackground();
        int color = bgColorDrawable.getColor();
        if (color == getResources().getColor(R.color.green)) {
            this.setBackgroundColor(getResources().getColor(R.color.yellow));
            return R.color.yellow;
        } else if (color == getResources().getColor(R.color.yellow)) {
            this.setBackgroundColor(getResources().getColor(R.color.green));
            return R.color.green;
        } else {
            if (Math.random() < 0.5) {
                this.setBackgroundColor(getResources().getColor(R.color.yellow));
                return R.color.yellow;
            } else {
                this.setBackgroundColor(getResources().getColor(R.color.green));
                return R.color.green;
            }
        }
    }
}
