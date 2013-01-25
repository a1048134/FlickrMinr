package pae.team8.flickrminr.ui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{
	private BufferedImage img;
	public ImagePanel(BufferedImage image){
		img = image;
	}
	public void loadImage(BufferedImage image){
		img = image;
		this.repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, ((super.getWidth()/2) - (img.getWidth()/2)),((super.getHeight()/2) - (img.getHeight()/2)),this);
	}
}
