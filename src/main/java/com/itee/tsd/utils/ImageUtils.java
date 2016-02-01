package com.itee.tsd.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.IOUtils;

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
	        ImageIO.write(to, "png", new File(destPath));
	        //压缩质量
//	        FileOutputStream out = new FileOutputStream(destPath);
//	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(to);
//            jep.setQuality(1f, true);
//            encoder.encode(to, jep);
//            out.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void aliResizeImg(String srcPath, String destPath, Integer outputWidth, Integer outputHeight) {

		File in = new File(srcPath);//原图片
		File out = new File(destPath);//目的图片
		//将图像缩略到1024x1024以内，不足1024x1024则不做任何处理
		ScaleParameter scaleParam = new ScaleParameter(outputWidth, outputHeight);

		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		WriteRender wr = null;
		try {
			inStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			ImageRender rr = new ReadRender(inStream);
			ImageRender sr = new ScaleRender(rr, scaleParam);
			wr = new WriteRender(sr, outStream);

			wr.render();//触发图像处理
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream);//图片文件输入输出流必须记得关闭
			IOUtils.closeQuietly(outStream);
			if (wr != null) {
				try {
					wr.dispose();//释放simpleImage的内部资源
				} catch (SimpleImageException ignore) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String srcPath = "E:\\1.jpg";
		String destPath = "E:\\2.jpg";
		System.out.println("Done="+new Date().getTime());
//		resizeImage(srcPath, destPath, 220, 220);
		aliResizeImg(srcPath, destPath, 220, 220);
		System.out.println("Done="+new Date().getTime());
	}

}
