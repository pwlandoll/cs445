package edu.jcu.plandoll16.homework5;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by peter on 3/17/16.
 */
public class YellowGreenButton extends SpecialButton {
    private int maxSize;
    private TextView bottomView;

    public YellowGreenButton(Context context, int maxSize) {
        super(context, maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public void switchColor() {
        ColorDrawable bgColorDrawable = (ColorDrawable)this.getBackground();
        int color = bgColorDrawable.getColor();
        if (color == getResources().getColor(R.color.green)) {
            this.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else if (color == getResources().getColor(R.color.yellow)) {
            this.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            if (Math.random() < 0.5) {
                this.setBackgroundColor(getResources().getColor(R.color.yellow));
            } else {
                this.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        // Half size
        bottomView = (TextView)findViewById(R.id.bottomView); //returns null??
        int size = bottomView.getHeight();
        if (size > maxSize / 10) {
            bottomView.setWidth(size / 2);
            bottomView.setHeight(size / 2);
        }
    }
}
