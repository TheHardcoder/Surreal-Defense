package ggui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class DropDown extends AbstractComponent {
	private Label selection;
	private Button arrowDown;
	private List<Button> options = new ArrayList<>();
	private int smallheight, overallheight;

	public DropDown(int x, int y, final String[] options, BufferedImage fontImage) {
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
		arrowDown = new Button(x+250, y, "", buttonImage, fontImage, new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < options.length; i++){
					if (DropDown.this.options.get(i).isVisible()){
						DropDown.this.options.get(i).setVisible(false);
						DropDown.this.height = smallheight;
					}
					else {
						DropDown.this.options.get(i).setVisible(true);
						DropDown.this.height = overallheight;
					}
					
				}
			}
		});
		smallheight = selection.getHeight();
		overallheight = smallheight;
		for (int i = 0; i < options.length; i++){
			final int nr = i;
			this.options.add(new Button(x+selection.getMargin(), y+overallheight, options[i], null, fontImage, new Runnable() {
				
				@Override
				public void run() {
					selection.setLabel(options[nr]);
				}
			}));
			overallheight = overallheight + selection.getHeight() + selection.getMargin();
			this.options.get(i).setVisible(false);
			this.options.get(i).setMinwidth(250-10);
		}
		width = 250 + arrowDown.getWidth();
		height = arrowDown.getHeight();
	}
	
	public void mouseMove(int x, int y){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseMove(x, y);
		if (!arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOut();
	}
	
	public void mouseOver(){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOver();
	}
	
	public void mouseOut(){
		if (!arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOut();
	}
	
	public void mouseClick(int x, int y, int button){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseClick(x, y, button);
	}

	@Override
	public void renderComponent(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(x, y, selection.getWidth()+arrowDown.getWidth(), selection.getHeight(), 20, 20);
		selection.render(g);
		arrowDown.render(g);
		for (Label l : options)
			l.render(g);
	}

	@Override
	public void updateComponent(long elapsedTime) {
		selection.update(elapsedTime);
		arrowDown.update(elapsedTime);
		for (Label l : options)
			l.update(elapsedTime);
	}

}
