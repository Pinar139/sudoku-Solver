/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stajokulu.sudokusolver;

import com.stajokulu.sudokusolver.singletons.SudokuHelper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author muhammet
 */
public class MainFrame extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        camArea = new javax.swing.JPanel();
        sudokuArea = new javax.swing.JPanel();
        openCamBtn = new javax.swing.JButton();
        grabSudokuBtn = new javax.swing.JButton();
        solveSudokuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StajOkulu17 Sudoku Solver");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setMaximumSize(new java.awt.Dimension(840, 490));
        setMinimumSize(new java.awt.Dimension(840, 490));
        setResizable(false);
        setSize(new java.awt.Dimension(840, 490));

        camArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        camArea.setMaximumSize(new java.awt.Dimension(400, 400));
        camArea.setMinimumSize(new java.awt.Dimension(400, 400));
        camArea.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout camAreaLayout = new javax.swing.GroupLayout(camArea);
        camArea.setLayout(camAreaLayout);
        camAreaLayout.setHorizontalGroup(
            camAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        camAreaLayout.setVerticalGroup(
            camAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        sudokuArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sudokuArea.setMaximumSize(new java.awt.Dimension(400, 400));
        sudokuArea.setMinimumSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout sudokuAreaLayout = new javax.swing.GroupLayout(sudokuArea);
        sudokuArea.setLayout(sudokuAreaLayout);
        sudokuAreaLayout.setHorizontalGroup(
            sudokuAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        sudokuAreaLayout.setVerticalGroup(
            sudokuAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        openCamBtn.setText("Open Cam");
        openCamBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCamBtnActionPerformed(evt);
            }
        });

        grabSudokuBtn.setText("Grab Sudoku");
        grabSudokuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabSudokuBtnActionPerformed(evt);
            }
        });

        solveSudokuBtn.setText("Solve!");
        solveSudokuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveSudokuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openCamBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grabSudokuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(camArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sudokuArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solveSudokuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sudokuArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(camArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openCamBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(solveSudokuBtn)
                        .addComponent(grabSudokuBtn)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void openCamBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openCamBtnActionPerformed
        webSource = new VideoCapture(0);
        //hangi kameranin kullanilacagi
        //0 = birincil kamera
        //mobil cihaz bağlayarak 1 yapilir
        myThread = new MainFrame.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
        openCamBtn.setEnabled(false); // start button

        grabSudokuBtn.setEnabled(true); // stop button


    }//GEN-LAST:event_openCamBtnActionPerformed

    private void grabSudokuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabSudokuBtnActionPerformed
        myThread.runnable = false;
        openCamBtn.setEnabled(true);
        grabSudokuBtn.setEnabled(false);
        solveSudokuBtn.setEnabled(true);
        webSource.release();

        try {
            Mat mat = grid.perspektifDuzeltme(SudokuHelper.getInstance().getPoints(),SudokuHelper.getInstance().getSrc());
            
            mat = grid.findContoursAndDraw(mat);
            System.out.println("Büyük sudoku  :  "  +mat);
            Highgui.imwrite("output/mat.png", mat);
            
            Highgui.imencode(".bmp", frame, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(
                            mem.toArray()));

            BufferedImage buff = (BufferedImage) im;
            
            buff = matToBufferedImage(mat, buff);
            
            Graphics g = sudokuArea.getGraphics();
            

            if (g.drawImage(buff, 0, 0, getWidth()-450,
                            getHeight()-125, 0, 0, buff.getWidth(),
							buff.getHeight(), null));
            
            
         //   Mat sudokuKaresi = gir
        } catch (Exception e) {
            //e.printStackTrace();
        }


    }//GEN-LAST:event_grabSudokuBtnActionPerformed

    private void solveSudokuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveSudokuBtnActionPerformed
//        BufferedImage img = image.getImg();
//        int rows = img.getHeight();
//        int cols = img.getWidth();
//        int type = CvType.CV_8UC3;
//        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
//        Mat src = new Mat(rows, cols, type);
//        src.put(0, 0, pixels);
//
//        Mat grids = new Mat(rows, cols, type);
//        DigitList digitList = new DigitList();
//
//        grids = grid.detectGrid(src);
//
//        digitList = digits.detectDigits(grids);

        Tesseract instance = Tesseract.getInstance(); //
 
        instance.setTessVariable("tessedit_char_whitelist", "123456789");
        int grid[][] = new int[9][9];
       
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               
                try {
                    File file = new File("output/mat"+i+"-"+j+".png");
                    String result = instance.doOCR(file);

                    //System.out.println(result);
                    grid[j][i]=Integer.parseInt(result.substring(0,1));

                } catch (TesseractException e) {
                    //System.err.println(e.getMessage());
                    grid[j][i]=0;
                }
            }
        }
        Mat im = Highgui.imread("sudoku.jpg");
        int[][] solved = SudokuSolver.solveSudoku(grid);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(solved[i][j]+ " ");
                
                Core.putText(im,String.valueOf(solved[j][i]),new Point(i*30+2,j*30+28),Core.FONT_HERSHEY_COMPLEX,0.8,new Scalar(0,0,255));
            }
            System.out.print("\n");
        }

         
         
            BufferedImage buff = new BufferedImage(270,270,CvType.CV_32SC1);
            buff = matToBufferedImage(im,buff);
            
            
            Graphics g = sudokuArea.getGraphics();
        
        if (g.drawImage(buff, 0, 0, getWidth()-450,
                getHeight()-125, 0, 0, buff.getWidth(),
                    buff.getHeight(), null));


        //digits.detect(src);
    }//GEN-LAST:event_solveSudokuBtnActionPerformed

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    ;
	//kutuphaneleri eklemek gerekiyor
	
	
	
	DetectDigits digits = new DetectDigits();
    DetectSudokuGrid grid = new DetectSudokuGrid();
    GrabbedImage image = new GrabbedImage();

    private MainFrame.DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() throws InterruptedException {

        initComponents();

    }

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;
        DetectSudokuGrid detectGrid = new DetectSudokuGrid();

        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);

                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(
                                    mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                                
                            int rows = buff.getHeight();
                            int cols = buff.getWidth();
                            int type = CvType.CV_8UC3;
                            byte[] pixels = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
                            Mat src = new Mat(rows, cols, type);
                            src.put(0, 0, pixels);

                            Mat grids = new Mat(rows, cols, type);
                            DigitList digitList = new DigitList();

                            grids = grid.detectGrid(src);

                            

// Create an empty image in matching format
                                buff=    MainFrame.matToBufferedImage(grids, buff);
                      
                                
                            Graphics g = camArea.getGraphics();

                            
                            if (g.drawImage(buff, 0, 0, getWidth() - 450,
                                    getHeight() - 120, 0, 0, buff.getWidth(),
                                    buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Error");
                        }
                    }
                }
            }
        }
    }

public static BufferedImage matToBufferedImage(Mat matrix, BufferedImage bimg)
{
    if ( matrix != null ) { 
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
        case 1:  
            type = BufferedImage.TYPE_BYTE_GRAY;  
            break;  
        case 3:  
            type = BufferedImage.TYPE_3BYTE_BGR;  
            // bgr to rgb  
            byte b;  
            for(int i=0; i<data.length; i=i+3) {  
                b = data[i];  
                data[i] = data[i+2];  
                data[i+2] = b;  
            }  
            break;  
        default:  
            return null;  
        }  

        // Reuse existing BufferedImage if possible
        if (bimg == null || bimg.getWidth() != cols || bimg.getHeight() != rows || bimg.getType() != type) {
            bimg = new BufferedImage(cols, rows, type);
        }        
        bimg.getRaster().setDataElements(0, 0, cols, rows, data);
    } else { // mat was null
        bimg = null;
    }
    return bimg;  
}   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel camArea;
    private javax.swing.JButton grabSudokuBtn;
    private javax.swing.JButton openCamBtn;
    private javax.swing.JButton solveSudokuBtn;
    private javax.swing.JPanel sudokuArea;
    // End of variables declaration//GEN-END:variables
}
