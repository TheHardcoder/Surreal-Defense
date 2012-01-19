package ggui.main;

public interface InputListener {

	int getMouseX();
	int getMouseY();
	
	boolean isMousePressed(int button);
	boolean isMouseReleased(int button);
	
	int getMousePressed();
	int getMouseReleased();
	
	int getValueNoMouse();
	
	boolean isKeyDown(int keyCode);
	boolean isKeyPressed(int keyCode);
	boolean isKeyReleased(int keyCode);
	
	int getKeyPressed();
	int getKeyReleased();
	
	int getValueNoKey();
}
