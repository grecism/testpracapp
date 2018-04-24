package com.main.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * 
 *<p>Title	: WriteToFile</p>
 * @Description	:写文件
 * @author	: admin
 * @date	: 2017年11月24日下午2:34:08
 */
public class WriteToFile {
	
	public void main(){
		/**向文件中写入数据（字节流FileOutputStream）**/
		//向文件中写入数据（字节流FileOutputStream）
		//writeFileByBytes("D:/wfile/test/io/write","write text content to file!");
		//writeFileByBytesTwo("D:/wfile/test/io/write","write text content to file!");
		/** 向文件中写数据(FileWriter)【注意使用FileWriter("path",true)可以往文件后面追加内容，否则就直接覆盖了】**/
		//向文件中写数据(FileWriter)
		//writeFileByWriter("D:/wfile/test/io/write","write text content to file!");
		/**向文件中写数据(BufferedWriter)**/
		//向文件中写数据(BufferedWriter)
		writeFileByBufferedWrite("D:/wfile/test/io/write","write text content to file!");
	}
	
	//向文件中写入数据（字节流FileOutputStream）
	public void writeFileByBytes(String path,String content){
		File file = new File(path);
		String fileName=path+"/"+"iowritetest.txt";
		//File filetwo = new File(fileName);
		FileOutputStream out = null;
		try {
			if(!file.exists()){
				file.mkdirs();
			}
			/*if(!filetwo.exists()){
				file.createNewFile();//不写也会自动创建
			}*/
			//out = new FileOutputStream(filetwo);
			out = new FileOutputStream(fileName);
			out.write(content.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//向文件中写入数据（字节流FileOutputStream）
	public void writeFileByBytesTwo(String path,String content){
		File file = new File(path);
		String fileName = path+"/iowritetest.txt";
		FileOutputStream out = null;
		try {
			if(!file.exists()){
				file.mkdirs();
			}
			byte[] tempbytes = content.getBytes();
			out = new FileOutputStream(fileName);
			out.write(tempbytes, 0, (content.length()-1));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//向文件中写数据(FileWriter)
	public void writeFileByWriter(String path,String content){
		File file = new File(path);
		String fileName = path+"/iowritetest.txt";
		FileWriter writer = null;
		try {
			if(!file.exists()){
				file.mkdirs();
			}
			writer = new FileWriter(fileName,true);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//向文件中写数据(BufferedWriter)
	public void writeFileByBufferedWrite(String path,String content){
		File file = new File(path);
		String fileName = path+"/iowritetest.txt";
		BufferedWriter bufferedWriter = null;
		try {
			if(!file.exists()){
				file.mkdirs();
			}
			bufferedWriter = new BufferedWriter(new FileWriter(fileName));
			bufferedWriter.write(content);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
