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
import com.stajokulu.sudokusolver.singletons.SudokuHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
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

public class DetectSudokuGrid {

    private MatOfPoint2f biggest;

    public MatOfPoint2f getBiggest() {
        return biggest;
    }

    public void setBiggest(MatOfPoint2f biggest) {
        this.biggest = biggest;
    }

    public Mat siyahBeyazCevir(Mat src) {
//         Mat gray = new Mat();
//
//         
        Mat dst2 = new Mat();
        Imgproc.cvtColor(src, dst2, Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(dst2, dst2, new Size(3, 3), 0);
        Imgproc.adaptiveThreshold(dst2, dst2, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 25, 10);

//        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Highgui.imwrite("output/gray.png", dst2);
//
//        Imgproc.GaussianBlur(gray, gray, new Size(11, 11), 0);
        Highgui.imwrite("output/gauss.png", dst2);
//
//        Imgproc.adaptiveThreshold(gray, gray, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 5, 2);
        Highgui.imwrite("output/thresholded.png", dst2);

        return dst2;
    }

    public Mat detectGrid(Mat src) {

        Mat gray = siyahBeyazCevir(src);
        Core.bitwise_not(gray, gray);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat heirarchy = new Mat();
        Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.INTER_AREA);

//        Imgproc.findContours(gray, contours, heirarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        //  Highgui.imwrite("output/contours.png", gray);
        int h = src.height();
        int w = src.width();

        Double max = (double) 0;
        biggest = new MatOfPoint2f();
         Mat result =  new Mat();
            result = src.clone();

        try{
           
            

        double someOfArea = gray.size().area() * 5 / 100;

        //Max alanlı kontürü döner
        double[] maxs = maxAreaAndIdx(contours, someOfArea);
        double maxArea = maxs[0];
        int maxAreaIdx = (int) maxs[1];

        MatOfPoint2f approxCurve = new MatOfPoint2f();
        if (maxAreaIdx > -1) {
            MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(maxAreaIdx).toArray());
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
            //approxCurve.toArray() kontörün köşelerini döner
            MatOfPoint points = new MatOfPoint(approxCurve.toArray());
            Rect rect = Imgproc.boundingRect(points);
            if (maxArea > someOfArea) {
                Core.rectangle(result, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0),2);
            }
        }
        
//        for (MatOfPoint m : contours) {
//            MatOfPoint2f a = new MatOfPoint2f();
//
//            Imgproc.approxPolyDP(new MatOfPoint2f(m.toArray()), a, 4, true);
//            if (a.total() != 4) {
//                continue;
//            }
//
//            MatOfPoint am = new MatOfPoint();
//            a.convertTo(am, CvType.CV_32S);
//            if (!Imgproc.isContourConvex(am)) {
//                continue;
//            }
//            Double sizeApprox = Imgproc.contourArea(a);
//            if (sizeApprox > max) {
//                max = sizeApprox;
//                biggest = a;
//            }
//        }
//        Point[] points = biggest.toArray();
//        Mat copyImage = src.clone();
//        try {
//                MatOfPoint2f contour2f = biggest;
//                MatOfPoint2f approxCurve = new MatOfPoint2f();
//                double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
//                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
//                MatOfPoint matofpoint = new MatOfPoint(approxCurve.toArray());
//                Rect rect = Imgproc.boundingRect(matofpoint);
//
//                Core.rectangle(copyImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0),2);
            
//            Core.line(copyImage, new Point(points[0].x, points[0].y), new Point(points[1].x, points[1].y), new Scalar(255, 0, 0), 2);
//            Core.line(copyImage, new Point(points[1].x, points[1].y), new Point(points[2].x, points[2].y), new Scalar(255, 0, 0), 2);
//            Core.line(copyImage, new Point(points[2].x, points[2].y), new Point(points[3].x, points[3].y), new Scalar(255, 0, 0), 2);
//            Core.line(copyImage, new Point(points[3].x, points[3].y), new Point(points[0].x, points[0].y), new Scalar(255, 0, 0), 2);
//        } catch (Exception e) {
//            // e.printStackTrace();
//        }
        //Highgui.imwrite("output/line.png", copyImage);

//        Runnable mythread = new Runnable() {
//            @Override
//            public void run() {
//                perspektifDuzeltme(points, biggest);
//            }
//        };
//        new Thread(mythread).start();
        Point[] points =approxCurve.toArray();
        SudokuHelper.getInstance().setGrabbedMatImage(result);
        SudokuHelper.getInstance().setPoints(points);
        SudokuHelper.getInstance().setSrc(src);
        }catch(Exception e ){
            
        }
        return result;

    }

    
	/*Max alanlı kontürü döner double arrayi döner arrayin
    0. indisinde maxArea,
    1. indisinde maxAreaIdx vardır. */
    //Todo: some of areaya gerek yok daha sonra test et.
    private double[] maxAreaAndIdx(List<MatOfPoint> contours, double someOfArea) {
        double maxArea = -1;
        double maxAreaIdx = -1;
        for (int idx = 0; idx < contours.size(); idx++) {
            Mat contour = contours.get(idx);

            double contourArea = Imgproc.contourArea(contour);
            if (contourArea > maxArea && contourArea > someOfArea) {
                maxArea = contourArea;
                maxAreaIdx = idx;
            }
        }
        double[] result = {maxArea, maxAreaIdx};
        return result;
    }
    
    public Mat perspektifDuzeltme(Point[] points, Mat src) {
        List<Point> pointList = biggest.toList();
        int y = 0, s2 = 0;
        double min = points[0].y;
        for (int i = 1; i < 4; i++) {
            if (min > points[i].y) {
                y = i;
                min = points[i].y;
            }
        }
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            if (i != y) {
                if (!flag) {
                    min = Math.abs(points[y].y - points[i].y);
                    flag = true;
                    s2 = i;
                } else if (min > Math.abs(points[y].y - points[i].y)) {
                    s2 = i;
                    min = Math.abs(points[y].y - points[i].y);
                }
            }
        }
        List<Point> pointListOri = new ArrayList<>();
        if (points[y].x > points[s2].x) {
            pointListOri.add(points[s2]);
            pointListOri.add(points[y]);
        } else {
            pointListOri.add(points[y]);
            pointListOri.add(points[s2]);
        }
        Integer[] a = {0, 0, 0, 0};
        a[y] = 1;
        a[s2] = 1;
        int j = 0;
        Integer[] s3 = new Integer[2];
        for (int i = 0; i < 4; i++) {
            if (a[i] == 0) {
                s3[j++] = i;
            }
        }
        if (points[s3[0]].x > points[s3[1]].x) {
            pointListOri.add(points[s3[0]]);
            pointListOri.add(points[s3[1]]);
        } else {
            pointListOri.add(points[s3[1]]);
            pointListOri.add(points[s3[0]]);
        }

        MatOfPoint2f s = new MatOfPoint2f();
        s.fromList(pointListOri);
        Size reshape = new Size(270, 270);
        Mat undistorted = new Mat(reshape, CvType.CV_8UC1);
        //Highgui.imwrite("output/undistorted.png", undistorted);

        MatOfPoint2f d = new MatOfPoint2f();
        d.fromArray(new Point(0, 0), new Point(reshape.height, 0),
                new Point(reshape.width, reshape.height), new Point(0, reshape.width));
        Mat tranformMatrix = Imgproc.getPerspectiveTransform(s, d);
        //Highgui.imwrite("output/transfrommatrix.png", tranformMatrix);

        Imgproc.warpPerspective(src, undistorted, tranformMatrix, reshape);
        Highgui.imwrite("output/undistorted2.png", undistorted);

        SudokuHelper.getInstance().setBigSudokuKaresi(undistorted);
        return undistorted;
    }

    
    public Mat findContoursAndDraw(Mat src) {
        // Mat gray= siyahBeyazCevir(src);
        DigitList sayiListesi = new DigitList();
        
        Mat dst2 = new Mat();
        Imgproc.cvtColor(src, dst2, Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(dst2, dst2, new Size(3, 3), 0);
        Imgproc.adaptiveThreshold(dst2, dst2, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 25, 10);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat dst = new Mat();
        dst2.copyTo(dst);

        Imgproc.findContours(dst, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.INTER_AREA);
        Size imageArea = src.size();
int counter =0;
        double area = imageArea.area();
        double minArea = imageArea.area() * 0.07 / 100;
        double ustTaban = (area * 0.35) / 100;
        // Draw all the contours such that they are filled in.
        //Mat contourImg = new Mat(dst.size(), dst.type());
        for (int i = 0; i < contours.size(); i++) {
            //Imgproc.drawContours(src, contours, i, new Scalar(255, 255, 255), -1);

            Mat contour = contours.get(i);
            double contourArea = Imgproc.contourArea(contour);
            if (contourArea < ustTaban && contourArea > minArea) {
                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
                MatOfPoint2f approxCurve = new MatOfPoint2f();
                double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
                MatOfPoint points = new MatOfPoint(approxCurve.toArray());
                Rect rect = Imgproc.boundingRect(points);
                Core.rectangle(src, new Point(rect.x-2, rect.y-2), new Point(rect.x + rect.width+2, rect.y + rect.height+2), new Scalar(0, 0, 255));
                
                Mat submat = dst2.submat(rect);
                Mat mat = new Mat();

                submat.copyTo(mat);
                int[] koordinat = new SubMat(mat,rect).getMatrixIndexs(src.width(), src.height());
                Highgui.imwrite("output/mat"+koordinat[0]+"-"+koordinat[1]+".png", mat);
                sayiListesi.add(mat, rect);
                counter++;
            }

        }
        
        SudokuHelper.getInstance().setDigitList(sayiListesi);
        
        return src;
    }

}

