/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.print.Collation;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 *
 * 
 */
public class DigitList {
    private List<SubMat> digits;
   

    public DigitList() {
        digits = new ArrayList<>();
        
    }
    public void sort(){
        Collections.sort(digits);
    }
    public void add(Mat img, Rect rect){
        digits.add(new SubMat(img,rect));
    }
    public List<SubMat> getDigits(){
        return digits;
    }
    public void setDigits(List<SubMat> digits){
        this.digits=digits;
    }
    
}
