package my.coursera.week5;

import java.io.File;

public class MyMainClass {

	public static void main(String[] args) {
		String[] fileNames = new String[] {
				//"D:\\Ravi\\My_Programs\\Eclipse_Workspace\\Coursera Project_Algorithms Part I\\resources\\week5\\kdtree\\circle10.txt"
				System.getProperty("user.dir") + File.separator + "resources" + File.separator + "week5" + File.separator + "kdtree" + File.separator + "circle10.txt"
		};

		for (String file : fileNames) {
			//RangeSearchVisualizer.main(new String[] { file });
			NearestNeighborVisualizer.main(new String[] { file });
		}
	}

}
