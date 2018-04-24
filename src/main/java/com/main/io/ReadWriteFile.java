package com.main.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 
 *<p>Title	: ReadWriteFile</p>
 * @Description	:读写文件
 * @author	: admin
 * @date	: 2017年11月24日下午2:33:56
 */
public class ReadWriteFile {
	
	public void main(){
		//读写文件(BufferedReader BufferedWriter)
		//readWriteFromFile("D:/wfile/test/ioreadtest.txt","D:/wfile/test/io/readwrite");
		//readWriteFromFile("D:/wfile/test/io.jpg","D:/wfile/test/io/readwrite"); // 图片损坏
		//读写图片(BufferedInputStream BufferedOutputStream)
		readWriteFromImage("D:/wfile/test/io.jpg","D:/wfile/test/io/readwrite");
	}
	
	//读写文件(BufferedReader BufferedWriter)
	public void readWriteFromFile(String srFileName,String desPath){
		File desfile = new File(desPath);
		String toFileName = desPath+"/ioreadwritetest.txt";
		//String toFileName = desPath+"/ioreadwrite.jpg";  // 图片损坏
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			if(!desfile.exists()){
				desfile.mkdirs();
			}
			bufferedReader = new BufferedReader(new FileReader(srFileName));
			bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
			char[] tempbytes = new char[1024];
			int len;
			while((len = bufferedReader.read(tempbytes)) != -1){
				bufferedWriter.write(tempbytes, 0, len);
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读写图片(BufferedInputStream BufferedOutputStream)
	public void readWriteFromImage(String srFileName,String desPath){
		File desfile = new File(desPath);
		String toFileName = desPath+"/ioreadwrite.jpg";
		BufferedInputStream  bufInputStream = null;
		BufferedOutputStream bufOutputStream = null;
		try {
			if(!desfile.exists()){
				desfile.mkdirs();
			}
			bufInputStream = new BufferedInputStream(new FileInputStream(srFileName));
			bufOutputStream = new BufferedOutputStream(new FileOutputStream(toFileName));
			
			byte[] tempbytes = new byte[1024];
			int len = 0;
			while((len = bufInputStream.read(tempbytes)) != -1){
				bufOutputStream.write(tempbytes, 0, len);
			}
			bufOutputStream.flush();
			bufOutputStream.close();
			bufInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
