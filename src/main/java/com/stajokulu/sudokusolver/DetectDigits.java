/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.effect.GaussianBlur;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.INTER_NEAREST;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.resize;
import sun.awt.geom.Crossings;

/**
 *
 * 
 */
public class DetectDigits {

    DetectSudokuGrid grid = new DetectSudokuGrid();
            
    DigitList digitList;
    
    public DigitList detectDigits(Mat src) {
       // Mat gray = new Mat();
        DigitList digitList = new DigitList();
        //Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        

        //Imgproc.GaussianBlur(gray, gray, new Size(11, 11), 0);
        

        //Imgproc.adaptiveThreshold(gray, gray, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 5, 2);
        
        List<MatOfPoint> contours = new ArrayList<>();
        Mat heirarchy = new Mat();
        Imgproc.findContours(src, contours, heirarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));

        
        Size imageArea = src.size();
        double area = imageArea.area();
        double minArea = imageArea.area()*0.05/100;
        double ustTaban = (area*0.25)/100;
        for (int idx = 0; idx < contours.size(); idx++) {
            Mat contour = contours.get(idx);
            double contourArea = Imgproc.contourArea(contour);
            if (contourArea<ustTaban && contourArea>minArea) {
                MatOfPoint2f approxCurve = new MatOfPoint2f();
                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(idx).toArray());
                double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
                MatOfPoint points = new MatOfPoint(approxCurve.toArray());
                Rect rect = Imgproc.boundingRect(points);
                double pay = rect.area()*0.7/100;
                double rectArea = rect.area();
                double edge = Math.sqrt(rectArea);
                if (Math.abs(rect.width-rect.height)<edge*97/100) {
                    Mat submat = src.submat(rect);
                    Mat mat = new Mat();
                    submat.copyTo(mat);
                    digitList.add(mat,rect);
                    Highgui.imwrite("output/digit-"+idx+".png", submat);
                }   
            }
        }

        this.digitList = digitList;

        return digitList;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        Mat smallimage, smallt = new Mat(), thresholded32 = new Mat();
        int m=0; int n;
        for (; m < 180; m = m + 20)
        {
            for (n = 0; n < 180; n = n + 20)
              {
                 smallimage = new Mat(gray, new Rect(n, m, 20, 20));
                 //Highgui.imwrite("output/small_"+m+"_"+n+".png", smallimage);
                 //smallt.push_back(smallimage);
                 
                 Imgproc.GaussianBlur(smallimage, smallimage, new Size(11, 11), 3);
        

                 Imgproc.adaptiveThreshold(smallimage, smallimage, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 11, 2);
                 //Highgui.imwrite("output/thresholded_"+m+"_"+n+".png", smallimage);
                
                  List<MatOfPoint> contours = new ArrayList<>();
                  Mat heirarchy = new Mat();
                  Imgproc.findContours(smallimage, contours, heirarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
                  Highgui.imwrite("output/contours_"+m+"_"+n+".png", smallimage);
                 
                  System.out.println(m+"_"+n+"'deki\t1 sayısı =\t"+Core.countNonZero(smallimage)+"\t0 sayısı =\t"+((smallimage.height()*smallimage.width())-Core.countNonZero(smallimage))+ "\ttotal pixel=\t"+((smallimage.height()*smallimage.width())));
                  
                  if(Core.countNonZero(smallimage)>50){
                      Core.line(src, new Point(m+3, n+3), new Point(m+3, n+17), new Scalar(0,255,  0), 1);
                      Core.line(src, new Point(m+3, n+17), new Point(m+17, n+17), new Scalar(0,255,  0), 1);
                      Core.line(src, new Point(m+17, n+17), new Point(m+17, n+3), new Scalar(0,255,  0), 1);
                      Core.line(src, new Point(m+17, n+3), new Point(m+3, n+3), new Scalar(0,255,  0), 1);
                  }                  
               }
            
         }
        */
     //   Highgui.imwrite("output/last.png", src);
//        return gray;
    }
    
}
