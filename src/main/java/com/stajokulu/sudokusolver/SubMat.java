/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class SubMat implements Comparable<SubMat> {
    private Mat img;
    private Rect rect;

    public SubMat(Mat img,Rect rect) {
        this.img = img;
        this.rect = rect;
    }

    public Mat getImg() {
        return img;
    }

    public void setImg(Mat img) {
        this.img = img;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int[] getMatrixIndexs(int imageWidth,int imageHeight){
        double x=  rect.x;
        double y = rect.y;
        double width = imageWidth;
        double height =imageHeight;
        double offsetx = x+rect.width/4;
        double offsety= y+rect.width/4;

        int []indexs = new int[2];
        indexs[0]   = (int)offsetx/(int)(width/9);
        indexs[1] = (int)offsety/(int)(height/9);
        if(indexs[0]>8){
            indexs[0] =8;
        }
        if(indexs[1]>8){
            indexs[1] =8;
        }
        return indexs;
    }

    @Override
    public int compareTo(SubMat another) {
        byte epsilon  = 11;
        if(another== null)
            return 1;

        SubMat obj = another;
        int fark = Math.abs(this.rect.y-obj.getRect().y);
        if(fark<=epsilon){
            return compareX(obj);
        }

        if(this.rect.y>obj.getRect().y){
            return 1;
        }else if(this.rect.y<obj.getRect().y){
            return -1;
        }else{
            return compareX(obj);
        }
    }
    private int compareX(SubMat obj){
        if(this.rect.x>obj.getRect().x){
            return 1;
        }else if(this.rect.x<obj.getRect().x){
            return -1;
        }
        else{
            return 0;
        }
    }
}
