package surrealdefense.main;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import surrealdefense.tools.ColorParser;

public class Defaults {
	private static Properties properties = new Properties();
	private static File propFile = new File("resources/defaults.properties"); 
	public static int windowWidth;
    public static int windowHeight;
    public static boolean fullscreen;
    public static Color backgroundColor1;
    public static Color backgroundColor2;
    
    public static void loadProperties(){
        FileInputStream fis;
        try {
        	propFile.createNewFile();
        	fis = new FileInputStream(propFile);
            properties.load(fis);
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (properties.getProperty("windowWidth") == null)
        	properties.setProperty("windowWidth", ""+1024);
        if (properties.getProperty("windowHeight") == null)
        	properties.setProperty("windowHeight", ""+768);
        if (properties.getProperty("fullscreen") == null)
        	properties.setProperty("fullscreen", ""+false);
        if (properties.getProperty("backgroundColor1") == null)
        	properties.setProperty("backgroundColor1", "100;100;100");
        if (properties.getProperty("backgroundColor2") == null)
        	properties.setProperty("backgroundColor2", "50;50;50");
        windowWidth = Integer.parseInt(properties.getProperty("windowWidth"));
        windowHeight = Integer.parseInt(properties.getProperty("windowHeight"));
        fullscreen = Boolean.parseBoolean(properties.getProperty("fullscreen"));
        backgroundColor1 = ColorParser.parseColor(properties.getProperty("backgroundColor1"));
        backgroundColor2 = ColorParser.parseColor(properties.getProperty("backgroundColor2"));
        
    }
    
    public static void saveProperty(){
    	try { 
        	FileOutputStream fos = new FileOutputStream(propFile);
			properties.store(fos, "The properties for the game.");
			fos.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

}
