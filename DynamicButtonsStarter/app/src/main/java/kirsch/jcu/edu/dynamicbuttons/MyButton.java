package kirsch.jcu.edu.dynamicbuttons;

import android.content.Context;
import android.widget.Button;

/**
 * Created by peter on 3/7/16.
 */
public class MyButton extends Button  {
    private String name;

    public MyButton(Context context, String name) {
        super(context);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
