package com.itee.tsd.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

	/**
	 * 等比例缩放
	 * @param srcPath
	 * @param destPath
	 * @param outputWidth
	 * @param outputHeight
	 */
	public static void resizeImage(String srcPath, String destPath, Integer outputWidth, Integer outputHeight) {
		try {
			File srcFile = new File(srcPath);
	        BufferedImage bi = ImageIO.read(srcFile);
	        double rate1 = ((double) bi.getWidth(null)) / (double) outputWidth + 0.1;
	        double rate2 = ((double) bi.getHeight(null)) / (double) outputHeight + 0.1;
	        double rate = rate1 < rate2 ? rate1 : rate2;
	        int newWidth = (int) (((double) bi.getWidth(null)) / rate);
	        int newHeight = (int) (((double) bi.getHeight(null)) / rate);
	        BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = to.createGraphics();
	        Image from = bi.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	        g2d.drawImage(from, 0, 0, null);
	        g2d.dispose();
	     //   ImageIO.write(to, "png", new File(destPath));
	        //压缩质量
	        FileOutputStream out = new FileOutputStream(destPath);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(to);
            jep.setQuality(1f, true);
            encoder.encode(to, jep);
            out.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String srcPath = "D:\\test_10.jpg";
		String destPath = "D:\\test_10_3.jpg";
		System.out.println("Done="+new Date().getTime());
		resizeImage(srcPath, destPath, 230, 230);
		System.out.println("Done="+new Date().getTime());
	}

}
