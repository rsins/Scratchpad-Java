package com.myexample.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyMainClass {
	private final static String FILE_NAME = System.getProperty("user.dir") + "\\src\\com\\myexample\\nio\\MyFile.txt";
	
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;
		myPrintStream.println("*************************************");
		myPrintStream.println("* Byte Buffer and File Channel");
		myPrintStream.println("*************************************");
		tryByteBufferFileChannel(FILE_NAME, myPrintStream);
		myPrintStream.println("*************************************");
		myPrintStream.println("* Memory Mapped Byte Buffer and File Channel");
		myPrintStream.println("*************************************");
		tryMemoryMappedByteBufferFileChannel(FILE_NAME, myPrintStream);
		myPrintStream.println("*************************************");
	}

	private static void tryByteBufferFileChannel(String aFileName, PrintStream aPrintStream) {
		try {
			FileInputStream myFileInputStream = new FileInputStream(aFileName);
			FileChannel myFileChannel = myFileInputStream.getChannel();
			ByteBuffer myByteBuffer = ByteBuffer.allocate(1024);
			
			myByteBuffer.clear();
			
			while (myFileChannel.read(myByteBuffer) != -1){
				myByteBuffer.flip();
				
				aPrintStream.write(myByteBuffer.array(),myByteBuffer.position(),myByteBuffer.remaining());
				myByteBuffer.clear();
			}
			
			myFileChannel.close();
			
		} catch (FileNotFoundException myFileNotFoundException) {
			myFileNotFoundException.printStackTrace();
		} catch (IOException myIOException) {
			myIOException.printStackTrace();
		}
	}
	
	private static void tryMemoryMappedByteBufferFileChannel(String aFileName, PrintStream aPrintStream) {
		try {
			FileInputStream myFileInputStream = new FileInputStream(aFileName);
			FileChannel myFileChannel = myFileInputStream.getChannel();
			long myFileSize = myFileChannel.size();
			ByteBuffer myByteBuffer = myFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, myFileSize);

			for (int i = 0; i < myFileSize; i++) {
				aPrintStream.print((char)myByteBuffer.get());
			}
			
			myFileChannel.close();
		} catch (FileNotFoundException myFileNotFoundException) {
			myFileNotFoundException.printStackTrace();
		} catch (IOException myIOException) {
			myIOException.printStackTrace();
		}		
	}
}
