/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver.singletons;

import com.stajokulu.sudokusolver.DigitList;
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.core.Point;

/**
 *
 * 
 */
public class SudokuHelper {
   private static SudokuHelper instance = null;
   public String name;
Mat grabbedMatImage;
    Mat bigSudokuKaresi;
    
    Point[] points;
    Mat src;
    DigitList digitList;
    
    

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Mat getSrc() {
        return src;
    }

    public void setSrc(Mat src) {
        this.src = src;
    }

    public DigitList getDigitList() {
        return digitList;
    }

    public void setDigitList(DigitList digitList) {
        this.digitList = digitList;
    }
    
    
    
   protected SudokuHelper() {
      // Exists only to defeat instantiation.
   }
   public static SudokuHelper getInstance() {
      if(instance == null) {
         instance = new SudokuHelper();
      }
      return instance;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mat getGrabbedMatImage() {
        return grabbedMatImage;
    }

    public void setGrabbedMatImage(Mat grabbedMatImage) {
        this.grabbedMatImage = grabbedMatImage;
    }


    public Mat getBigSudokuKaresi() {
        return bigSudokuKaresi;
    }

    public void setBigSudokuKaresi(Mat bigSudokuKaresi) {
        this.bigSudokuKaresi = bigSudokuKaresi;
    }
   
}
