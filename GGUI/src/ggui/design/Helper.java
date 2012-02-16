package ggui.design;

import java.awt.Color;

public class Helper {
	public static Color[] createColorArray(Color c1, Color c2, int steps){
        Color[] c = new Color[steps];
        c[0] = c1;
        c[steps-1] = c2;
        int red = c1.getRed();
        int green = c1.getGreen();
        int blue = c1.getBlue();
        int alpha = c1.getAlpha();
        double dred = ((double) c2.getRed()-red)/steps;
        double dgreen = ((double)c2.getGreen()-green)/steps;
        double dblue = ((double)c2.getBlue()-blue)/steps;
        double dalpha = ((double)c2.getAlpha()-alpha)/steps;
        for (int i = 1; i < steps-1; i++){
            c[i] = new Color(
                    (int) (red + dred * i),
                    (int) (green + dgreen * i),
                    (int) (blue + dblue * i),
                    (int) (alpha + dalpha * i));
        }
        return c;
    }
}
