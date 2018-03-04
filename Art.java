/******************************************************************************
 *  Name:    Ellie Shapiro
 *  NetID:   eliannes
 *  Precept: P13
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Program that takes integer n and draws a recursive curve 
 *                stitched pattern.
 *                
 *      
 ******************************************************************************/


public class Art {
    // draws a curve stitch pattern and calls itslef recursively
    private static void draw(int n, double[] x, double[] y, double screenWidth) {
        if (n == 0) return;
        
        // alternate color depending on n
        if (n % 2 == 0) {
            StdDraw.setPenColor(200, 30, 60);
        }
        if (n % 2 != 0) {
            StdDraw.setPenColor(30, 80, 200);
        }
        
        // draw line at top and bottom for completeness
        double shiftedX = x[0] + screenWidth;
        double shiftedY = y[0] + screenWidth;
        StdDraw.line(x[0], y[0], shiftedY, y[0]);
        StdDraw.line(x[0], shiftedY, shiftedX, shiftedY);
        
        // make copies for later
        double[] copyX = Transform2D.copy(x);
        double[] copyY = Transform2D.copy(y);
        
        // draw curve stitch pattern
        while (x[0] <= 0.5) { 
            // bottom left quarter
            StdDraw.line(x[0], y[0], x[1], y[1]);
            
            // top left quarter
            reflectHorizontal(y);
            StdDraw.line(x[0], y[0], x[1], y[1]);
            
            // top right quarter
            reflectVertical(x);
            StdDraw.line(x[0], y[0], x[1], y[1]);
            
            // bottom right quarter
            reflectHorizontal(y);
            StdDraw.line(x[0], y[0], x[1], y[1]);
            
            // reflect back to original
            reflectVertical(x);
            
            x[0] = x[0] + 0.02*screenWidth;
            y[1] = y[1] - 0.02*screenWidth;
        }
       
        
        // scale x and y coordinates to smaller screen
        double shift = 0.13*screenWidth;
        double[] newX = { (copyX[0] + shift), (copyX[1] + shift) };
        double[] newY = { (copyY[0] + shift), copyY[1] };

        // draw curve stitch with new coordinates/smaller screen
        draw(n-1, newX, newY, 0.74*screenWidth);
    }
    
    
    // takes x coordinate array to reflect x coordinates over x = 0.5
    private static void reflectVertical(double[] x) {
        double[] xPrime = Transform2D.copy(x);
        for (int i = 0; i < x.length; i++) {
            x[i] = 1.0 - xPrime[i];
        }
    }
    
    // takes y coordinate array to reflect y coordinates over y = 0.5
    private static void reflectHorizontal(double[] y) {
        double[] yPrime = Transform2D.copy(y);
        for (int i = 0; i < y.length; i++) {
            y[i] = 1.0 - yPrime[i];
        }
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.clear(StdDraw.BLACK);
        
        double[] x = { 0.0, 0.0 };
        double[] y = { 0.0, 0.5 };
        
        draw(n, x, y, 1.0);
    }
}