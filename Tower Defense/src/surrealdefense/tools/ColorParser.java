package surrealdefense.tools;

import java.awt.Color;

/**
 *
 * @author D056866
 */
public class ColorParser {
    
    public static Color parseColor(String colorString){
        String[] s = colorString.split(";");
        Color c;
        if (s.length < 4)
            c = new Color(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        else
            c = new Color(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
        return c;
    }
}
