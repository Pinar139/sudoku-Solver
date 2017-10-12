///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.stajokulu.sudokusolver;
//
//import java.util.List;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfPoint;
//import org.opencv.core.MatOfPoint2f;
//import org.opencv.core.Rect;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//
///**
// *
// * 
// */
// public class DigitRecognizer {
//
//    private Mat image;
//    //private OpticalCharacterRecognition opticalCharacterRecognition;
//    private DigitList digitList;
//
//    public DigitRecognizer() {
//    }
//
//    public DigitRecognizer(Mat image) {
//        this.image = image;
//    }
//
//    
////
////    public DigitRecognizer(Mat image, OpticalCharacterRecognition opticalCharacterRecognition){
////        this.image = image;
////        this.opticalCharacterRecognition = opticalCharacterRecognition;
////    }
//
//    public DigitList getDigitList() {
//        return digitList;
//    }
//
//    public void setDigitList(DigitList digitList) {
//        this.digitList = digitList;
//    }
//
////    public Bitmap rotateAndFindCorrect(){
////        Bitmap bitmapImage = MyProc.MatToBitmap(image, Bitmap.Config.ARGB_8888);
////        DigitList list;
////        //Cameradan çekilen resim 90 derece sola dönük olarak geliyor.İlk başta onu düzeltmek için
////        //90 derece çeviriyorum.Sonra perspectiveDüzeltme yapınca yön konusunda sıkıntı oluyor.Düzgün yönü bulmak için
////        //bulana kadar çeviriyoruz.
////        bitmapImage = MyProc.rotateBitmap(bitmapImage,90);
////        boolean correct = false;
////        for(int i = 0; i<4;i++){
////            list = check(bitmapImage);
////            if(list!=null){
////                correct= true;
////                break;
////            }
////            bitmapImage = MyProc.rotateBitmap(bitmapImage,90);
////        }
////        if(!correct)
////            return null;
////
////        return bitmapImage;
////    }
//
//    //Açıyı konrol et.
////    private DigitList check(Bitmap bitmapImage){
////
////        List<SubMat> subMats;
////        DigitList digitList;
////        Mat img = MyProc.bitmapToMat(bitmapImage);
////        digitList = digitRecognize(img);
////        digitList.sort();
////        subMats = digitList.getDigits();
////        int count = 0;
////        for(int i =0 ; i<5;i++ ) {
////            try {
////                String value = opticalCharacterRecognition.processImage(MyProc.MatToBitmap(subMats.get(0).getImg(), Bitmap.Config.ARGB_8888));
////                int val = Integer.parseInt(value);
////            }catch (Exception e) {
////                count++;
////                e.printStackTrace();
////            }
////
////            //5 tanesinden 3 tanesi çevrilemezse false resmin rotatetini yanlış kabul et.
////            if(count>2)
////                return null;
////
////        }
////         return digitList;
////    }
//
//
//    public DigitList digitRecognize(Mat img){
//        DigitList digitList = new DigitList();
//        Mat thresholdImage = MyProc.blurAndThreshold(img);
//        List<MatOfPoint> contours = MyProc.getContours(thresholdImage);
//        Size imageArea = img.size();
//        double area = imageArea.area();
//        double minArea= imageArea.area()*0.05/100;
//        double usttaban = (area*0.25)/100;
//        for (int idx = 0; idx < contours.size(); idx++) {
//            Mat contour = contours.get(idx);
//            double contourArea = Imgproc.contourArea(contour);
//            if(contourArea<usttaban && contourArea>minArea) {
//                MatOfPoint2f approxCurve = new MatOfPoint2f();
//                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(idx).toArray());
//                double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
//                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
//                MatOfPoint points = new MatOfPoint(approxCurve.toArray());
//                Rect rect = Imgproc.boundingRect(points);
//                double pay = rect.area()*0.7/100;
//                double rectarea = rect.area();
//                double edge =Math.sqrt(rectarea);
//                //Kareye yakın olmayan kotürleri çıkarır.
//                if(Math.abs(rect.width-rect.height)<edge*97/100 ) {
//                    //Imgproc.rectangle(img, new Point(rect.x - pay, rect.y - pay), new Point(rect.x + pay + rect.width, rect.y + pay + rect.height), new Scalar(255, 255, 255), 1);
//                    Mat submat = thresholdImage.submat(rect);
//                    Mat mat = new Mat();
//                    submat.copyTo(mat);
//
//                    digitList.add(mat, rect);
//                }
//            }
//        }
//        this.digitList = digitList;
//        return  digitList;
//    }
//
//
//}
