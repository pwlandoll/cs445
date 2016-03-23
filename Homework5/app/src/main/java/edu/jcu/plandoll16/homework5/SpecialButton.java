package edu.jcu.plandoll16.homework5;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by peter on 3/17/2016.
 */
public class SpecialButton extends Button {
    private int maxSize;

    public SpecialButton(Context context, int maxSize) {
        super(context);
        this.maxSize = maxSize;
    }

    public void switchColor() {}
}
