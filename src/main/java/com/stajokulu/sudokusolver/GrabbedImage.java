/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver;

/**
 *
 * 
 */
import java.awt.image.BufferedImage;

public class GrabbedImage {
	BufferedImage img;

	public GrabbedImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param img
	 */
	public GrabbedImage(BufferedImage img) { //burda imageleri alıyoruz.
		super();
		this.img=img;
	}

	/**
	 * @return the img
	 */
	public BufferedImage getImg() {//get metodu ile değer döndürüyoruz.
	      return img;
              
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(BufferedImage img) {//set metodu ile değer atıyoruz.
		this.img = img;
	}
	
}
