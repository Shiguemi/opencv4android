package org.opencv.samples.tutorial2;

import java.io.File;

import org.opencv.core.*;
import org.opencv.highgui.*;

public class FlannMatch {
	Mat needle; // look for a needle
	Mat haystack; // in a haystack
	
	public FlannMatch() {
	}
	
	public void setNeedle(String path) {
		// TODO: load this image into needle
		//needle = Highgui.imread(path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		File file = new File(path);
		Mat inputImage = Highgui.imread(file.getAbsolutePath());
		needle = new Mat(inputImage.height(), inputImage.width(), CvType.CV_8UC4);
		inputImage.convertTo(needle, CvType.CV_8UC4);
	}
	
	public void sethaystack(Mat _haystack) {
		haystack = _haystack; // TODO: investigate if this assignment is not copying.
	}
	
	// returns relative coordinate of needle in the haystack.
	public Mat relativeCoordinates() {
		// TODO: implement the relative coordinate find
		return null;
	}

	// stamp the image on 
	public Mat stamp(Mat mRgba) {
		// TODO Auto-generated method stub
//		double alpha = 0.5;
//		double beta = 1.0 - alpha;
//		int top = mRgba.height() / 2;
//		int left = 0;
//		Mat mRgbaDst = mRgba.submat(top, top + needle.height(), left, left + needle.width()); 
//		Core.addWeighted( needle, alpha, mRgbaDst, beta, 0.0, mRgbaDst);
		needle.copyTo(mRgba);

/*		// Put text 
		Point start = new Point(20, mRgba.height() / 2);
		double[] whiteColor = new double[4];
		whiteColor[0] = 255;
		whiteColor[1] = 255;
		whiteColor[2] = 255;
		whiteColor[3] = 1;	
		Scalar color = new Scalar(whiteColor);
		Core.putText(mRgba, "Hello", start, Core.FONT_HERSHEY_PLAIN, 12.0, color);
*/		return mRgba;
	}
}
