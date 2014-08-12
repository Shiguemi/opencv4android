package org.opencv.samples.tutorial2;

import java.io.File;

import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.*;
import org.opencv.imgproc.Imgproc;

import android.util.Log;

public class SurfMatch {
    private static final String    TAG = "OCVSample::FlannMatch";
    
	Mat needle; // looking for a needle
	Mat haystack; // in a haystack
	
	public SurfMatch() {
	}
	
	public void setNeedle(String path) {
		// TODO: load this image into needle
		//needle = Highgui.imread(path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		File file = new File(path);
		Mat inputImage = Highgui.imread(file.getAbsolutePath());
		needle = new Mat(inputImage.height(), inputImage.width(), CvType.CV_8UC1);
		inputImage.convertTo(needle, CvType.CV_8UC1);
		Imgproc.cvtColor(inputImage, needle, Imgproc.COLOR_RGBA2GRAY);
		Log.i(TAG, "needle.height()=" + needle.height() + " needle.width()=" + needle.width());
	}
	
	public void setHaystack(Mat _haystack) {
		haystack = _haystack; 
	}
	
	// returns relative coordinate of needle in the haystack.
	public Rect relativeCoordinates(Mat mRgba) {
		// TODO: implement the relative coordinate find
		Rect coordinates = new Rect();
		Mat img = new Mat(mRgba.height(), mRgba.width(), CvType.CV_8UC1);
		Imgproc.cvtColor(mRgba, img, Imgproc.COLOR_RGBA2GRAY); // grayscale
		FeatureDetector detector = FeatureDetector.create(FeatureDetector.SURF);
		
		MatOfKeyPoint keypoints = new MatOfKeyPoint();
		MatOfKeyPoint logoKeypoints = new MatOfKeyPoint();
		
		DescriptorExtractor surfExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
		detector.detect(img, keypoints);
		detector.detect(needle, logoKeypoints);
		Mat descriptors = new Mat();
		Mat logoDescriptors = new Mat();
		surfExtractor.compute(img, keypoints, descriptors);
		surfExtractor.compute(needle, logoKeypoints, logoDescriptors);
		
		return coordinates;
	}

	// stamp the image on 
	public Mat stamp(Mat mRgba) {
		// TODO Auto-generated method stub
//		double alpha = 0.5;
//		double beta = 1.0 - alpha;
		int top = mRgba.height() / 2;
		int left = 0;
		Rect roi = new Rect(left, left + needle.width(), top, top + needle.height());
		Mat src = mRgba.submat(roi);
		Mat dst = mRgba.submat(roi);
		Scalar color = new Scalar(255, 255, 255, 1);
		Point start = new Point(0, top);
		//Core.addWeighted( needle, alpha, src, beta, 1.0, dst);
		//needle.copyTo(dst);
		Core.putText(mRgba, "Hello", start, Core.FONT_HERSHEY_PLAIN, 12.0, color);
		src.release();
		dst.release();

		return mRgba;
	}
}
