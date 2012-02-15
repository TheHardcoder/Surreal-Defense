package surrealdefense.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import surrealdefense.dao.SaveGameDAO;

public class SaveGameManager {
	public static int MAX_SAVE_GAMES = 8;
	private static SaveGameDAO[] saveGames;
	private static File[] saveGameFiles;
	
	public static SaveGameDAO[] getSaveGames(){
		if (saveGames == null)
			loadSaveGames();
		return saveGames;
	}
	
	public static void saveGame(SaveGameDAO save){
		File folder = new File("savegames");
		if (!folder.exists())
			folder.mkdir();
		for (int i = 0; i < saveGames.length; i++) {
			if (saveGames[i].equals(save)){
				File f = new File("savegames/save" + i + ".sav");
				try {
					f.createNewFile();
					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(save);
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void loadSaveGames(){
		if (saveGameFiles == null){
			saveGameFiles = new File[MAX_SAVE_GAMES];
			for (int i = 0; i < MAX_SAVE_GAMES; i++){
				saveGameFiles[i] = new File("savegames/save" + i + ".sav");
			}
		}
		if (saveGames == null)
			saveGames = new SaveGameDAO[saveGameFiles.length];
		for (int i = 0; i < saveGameFiles.length; i++){
			ObjectInputStream fis;
			if (saveGameFiles[i].exists()){
				try {
					fis = new ObjectInputStream(new FileInputStream(saveGameFiles[i]));
					saveGames[i] = (SaveGameDAO) fis.readObject();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (saveGames[i] == null){
				saveGames[i] = new SaveGameDAO("Neues Spiel");
			}
		}
	}
}
