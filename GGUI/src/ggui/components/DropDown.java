package ggui.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class DropDown extends AbstractComponent {
	private Label selection;
	private Button dropDown;
	private List<Label> options = new ArrayList<>();

	protected DropDown(int x, int y, String[] options, BufferedImage fontImage) {
		super(x, y);
		selection = new Label(x, y, options[0], null, fontImage, false);
		selection.setMinwidth(250);
		BufferedImage buttonImage = null;
		try {
			buttonImage = ImageIO.read(new File("resources/images/arrow_down.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dropDown = new Button(x, y, "", buttonImage, fontImage, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		for (int i = 0; i < options.length; i++){
			this.options.add(new Label(x, y+selection.getHeight()* (i+1), options[i], null, fontImage, false));
		}
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
