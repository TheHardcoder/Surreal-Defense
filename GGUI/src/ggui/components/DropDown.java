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
	private int labelWidth = 250;

	public DropDown(int x, int y, final String[] optionsStrings, BufferedImage fontImage) {
		super(x, y);
		selection = new Label(x, y, optionsStrings[0], null, fontImage, false);
		selection.setMinwidth(labelWidth);
		BufferedImage buttonImage = null;
		try {
			buttonImage = ImageIO.read(new File("resources/images/arrow_down.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrowDown = new Button(x+labelWidth+selection.getMargin()/2, y + selection.getMargin()/2, "", buttonImage, fontImage, new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < optionsStrings.length; i++){
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
		smallheight = arrowDown.getHeight() + arrowDown.getMargin();
		overallheight = smallheight;
		width = labelWidth + selection.getMargin() + arrowDown.getWidth();
		for (int i = 0; i < optionsStrings.length; i++){
			final int nr = i;
			this.options.add(new Button(x, y+overallheight+selection.getMargin()/2, optionsStrings[i], null, fontImage, new Runnable() {
				
				@Override
				public void run() {
					selection.setLabel(optionsStrings[nr]);
					for (int i = 0; i < options.size(); i++){
						options.get(i).setVisible(false);
					}
					height = smallheight;
				}
			}));
			overallheight = overallheight + options.get(i).getHeight() + options.get(i).getMargin();
			this.options.get(i).setVisible(false);
			this.options.get(i).setMinwidth(width-selection.getMargin()/2);
		}
		
		height = smallheight;
	}
	
	public void mouseMove(int x, int y){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseMove(x, y);
		if (!arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOut();
		for (int i = 0; i < options.size(); i++){
			if (options.get(i).contains(inputListener.getMouseX(), inputListener.getMouseY()))
				options.get(i).mouseMove(x, y);
			if (!options.get(i).contains(inputListener.getMouseX(), inputListener.getMouseY()))
				options.get(i).mouseOut();
		}
	}
	
	public void mouseOver(){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOver();
		for (int i = 0; i < options.size(); i++){
			if (options.get(i).contains(inputListener.getMouseX(), inputListener.getMouseY()))
				options.get(i).mouseOver();
		}
	}
	
	@Override
	public void onFocusLoss() {
		for (int i = 0; i < options.size(); i++){
			options.get(i).setVisible(false);
		}
		height = smallheight;
	}

	public void mouseOut(){
		if (!arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseOut();
		for (int i = 0; i < options.size(); i++){
			if (!options.get(i).contains(inputListener.getMouseX(), inputListener.getMouseY()))
				options.get(i).mouseOut();
		}
	}
	
	public void mouseClick(int x, int y, int button){
		if (arrowDown.contains(inputListener.getMouseX(), inputListener.getMouseY()))
			arrowDown.mouseClick(x, y, button);
		for (int i = 0; i < options.size(); i++){
			if (options.get(i).contains(inputListener.getMouseX(), inputListener.getMouseY()))
				options.get(i).mouseClick(x, y, button);
		}
	}

	@Override
	public void renderComponent(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(x, y, width, height, 20, 20);
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
