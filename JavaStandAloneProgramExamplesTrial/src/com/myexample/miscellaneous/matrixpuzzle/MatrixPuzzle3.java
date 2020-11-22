package com.myexample.miscellaneous.matrixpuzzle;

public class MatrixPuzzle3 {

	/*
	 * Spiraly print n*n matrix. 
		Eg: [1,2,3,4] 
			[12,13,14,5] 
			[11,16,15,6] 
			[10,9,8,7] 

		Should print 
		1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
	 */
	public static void main(String[] args) {
		int[][] myMatrix = {{ 1,  2,  3,  4},
		                    {12, 13, 14,  5},
		                    {11, 16, 15,  6},
		                    {10,  9,  8,  7}};
		
		printMatrixSpiral(myMatrix);
		}
	
	private static void printMatrixSpiral(int[][] aMatrix) {
		int rowStart = 0, rowEnd = aMatrix.length - 1;
		int colStart = 0, colEnd = aMatrix[0].length - 1;
		
		do {
			for (int i = colStart; i <= colEnd ; i++) {
				System.out.println(aMatrix[rowStart][i]);
			}
			rowStart++;
			
			for (int i = rowStart; i <= rowEnd; i++) {
				System.out.println(aMatrix[i][colEnd]);
			}
			colEnd--;
			
			for (int i = colEnd; i >= colStart; i--) {
				System.out.println(aMatrix[rowEnd][i]);
			}
			rowEnd--;
			
			for (int i = rowEnd; i >= rowStart; i--) {
				System.out.println(aMatrix[i][colStart]);
			}
			colStart++;
		} while (rowStart <= rowEnd && colStart <= colEnd);
	}
}

