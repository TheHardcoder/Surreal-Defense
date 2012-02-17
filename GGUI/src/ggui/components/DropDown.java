package ggui.components;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class DropDown<Item> extends AbstractComponent {
	private Label selection;
	private Button arrowDown;
	private List<Button> options = new ArrayList<>();
	private Item[] items;
	private Item selectedItem;
	
	private int smallheight, overallheight;
	private int labelWidth = 250;

	public DropDown(int x, int y, Item[] pItems, BufferedImage fontImage) {
		super(x, y);
		this.items = pItems;
		selection = new Label(x, y, items[0].toString(), null, fontImage, false);
		selection.setMinwidth(labelWidth);
		children.add(selection);
		BufferedImage buttonImage = null;
		try {
			buttonImage = ImageIO.read(new File("resources/images/arrow_down.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		arrowDown = new Button(x+labelWidth, y , "", buttonImage, fontImage, new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < items.length; i++){
					if (DropDown.this.options.get(i).isVisible()){
						DropDown.this.options.get(i).setVisible(false);
						DropDown.this.height = smallheight;
					}
					else {
						DropDown.this.options.get(i).setVisible(true);
						DropDown.this.height = overallheight;
					}
					resize(width, height);
					renderComponent();
				}
			}
		});
		children.add(arrowDown);
		smallheight = arrowDown.getHeight();
		overallheight = smallheight;
		width = labelWidth + arrowDown.getWidth();
		for (int i = 0; i < items.length; i++){
			final int nr = i;
			this.options.add(new Button(x, y+overallheight+1, items[i].toString(), null, fontImage, new Runnable() {
				
				@Override
				public void run() {
					selectedItem = items[nr];
					selection.setLabel(items[nr].toString());
					for (int i = 0; i < options.size(); i++){
						options.get(i).setVisible(false);
					}
					height = smallheight;
					resize(width, height);
					renderComponent();
				}
			}));
			overallheight = overallheight + options.get(i).getHeight();
			this.options.get(i).setVisible(false);
			this.options.get(i).setMinwidth(width);
			children.add(options.get(i));
		}
		height = smallheight;
		resize(width, height);
		renderComponent();
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
		resize(width, height);
		renderComponent();
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
	public void renderComponent() {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(0, 0, width, height, 20, 20);
	}

	@Override
	public void updateComponent(long elapsedTime) {
		selection.update(elapsedTime);
		arrowDown.update(elapsedTime);
		for (Label l : options)
			l.update(elapsedTime);
	}
	
	public void setSelection(Item i){
		selectedItem = i;
		selection.setLabel(i.toString());
	}

	public Item getSelection(){
		return selectedItem;
	}
}
