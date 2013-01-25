package pae.team8.flickrminr.ui.model;

import net.logicdevelopment.biscotti.Model;
import pae.team8.flickrminr.data.DataSet;

import java.awt.image.BufferedImage;

public class MainModel implements Model{
    DataSet data;
	String imageId;
    BufferedImage realImage;

    public void setData(DataSet data){
        this.data = data;
    }
	public String getImageId(){
		return imageId;
	}
	
	public void setImageId(String imageId){
		this.imageId = imageId;
	}

    public void setRealImage(BufferedImage image){
        this.realImage = image;
    }
    public BufferedImage getRealImage(){
        return this.realImage;
    }
}
