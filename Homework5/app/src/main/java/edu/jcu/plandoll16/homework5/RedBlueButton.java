package edu.jcu.plandoll16.homework5;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by peter on 3/17/16.
 */
public class RedBlueButton extends SpecialButton {
    private int maxSize;
    private TextView bottomView;

    public RedBlueButton(Context context, int maxSize) {
        super(context, maxSize);
        this.maxSize = maxSize;
    }

    @Override
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
        // Double size
        bottomView = (TextView)findViewById(R.id.bottomView); //returns null???
        int size = bottomView.getHeight();
        if (size < 2 * maxSize) {
            bottomView.setHeight(size * 2);
            bottomView.setWidth(size * 2);
        }
    }
}
