package kirsch.jcu.edu.lineswithwatchersandwidget;

/**
 * Created by peter on 2/8/16.
 * kirsch.jcu.edu.lineswithwatchersandwidget.MyLine contains the state of a Line,
 * which includes a starting x and y value as well as an ending x and y value.
 * MyLine can calculate its own slope and y intercept as well as give the y value of a
 * corresponding inputted x value
 */
public class MyLine {
    // Instance variables
    private Double startX, startY, endX, endY, mySlope, myIntercept;
    private boolean isVerticalLine = false;

    // Constructors
    public MyLine(Double startX, Double startY, Double endX, Double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        calculate();
    }

    public MyLine() {
        // Default line goes through (0, 0) and (1, 1)
        this(0.0, 0.0, 1.0, 1.0);
    }

    private void calculate() {
        // Calculates slope
        // Should be called during constructor, and whenever any variable is set
        Double run = endX - startX;
        Double rise = endY - startY;
        if (run == 0) {
            mySlope = null;
            myIntercept = null;
        } else {
            mySlope = rise/run;
            myIntercept = startY - mySlope * startX;
        }
    }

    public Double getStartX() {
        return startX;
    }

    public void setStartX(Double startX) {
        this.startX = startX;
        calculate();
    }

    public Double getStartY() {
        return startY;
    }

    public void setStartY(Double startY) {
        this.startY = startY;
        calculate();
    }

    public Double getEndX() {
        return endX;
    }

    public void setEndX(Double endX) {
        this.endX = endX;
        calculate();
    }

    public Double getEndY() {
        return endY;
    }

    public void setEndY(Double endY) {
        this.endY = endY;
        calculate();
    }

    public Double getMySlope() {
        return mySlope;
    }

    public Double getMyIntercept() {
        return myIntercept;
    }

    public Double getResult(int xValue) {
        if (mySlope != null) {
            return mySlope * xValue + myIntercept;
        } else {
            return null;
        }
    }
}
