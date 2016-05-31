package com.xy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class DiskUtils {

	/**
	 * �ӷ�������Ӳ���ж�ȡ�洢�����ļ���,�ϴ���,�ϴ�ʱ��,�ļ���С��Ϣ�����л������ļ�
	 * @return ���ض�ȡ��������ļ�
	 */
	public static Object getListData() {
		Object obj = null;
		try {
			File file = new File("D:\\BSData\\listData.obj");
			ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
			obj = objIn.readObject();
			objIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return obj;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return obj;
		}
		return obj;
	}
	
	@Test
	public void test() throws Exception {
		File f = new File("D:\\BSData\\mt.txt");
		f.mkdir();
//		if (f.exists()) {
//			f.delete();
//		}
//		f.mkdir();
//		boolean b = f.exists();
//		System.out.println(b);
//		String path2 = f.getAbsolutePath();
//		String[] split = path2.split("\\\\");
//		for(String sp:split){
//			
//			System.out.println(sp);
//		}
//		File parentFile = f.getParentFile();
//		String path = parentFile.getAbsolutePath();
//		System.out.println(path);
		// f.createNewFile();

	}

	@Test
	public void test1(){
		try {
			// ��������
			ArrayList<HashMap<String[], String>> test = new ArrayList<HashMap<String[],String>>();
			String[] info = new String[]{"fileName", "fileSize", "time", "uploader"};
			HashMap<String[], String> hm = new HashMap<String[], String>();
			hm.put(info, "fileInfo");
			test.add(hm);
			
			DiskUtils.writeToDisk(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �������listDataд�뵽Ӳ����
	 * @param obj listData
	 * @return �����Ƿ�д��¹�
	 * @throws Exception FileNotFoundException
	 */
	public static boolean writeToDisk(Object obj) throws Exception {
		File file = new File("D:\\BSData\\listData.obj");
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));
		objOut.writeObject(obj);
		objOut.flush();
		objOut.close();
		return true;
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName){
		
		return false;
	}
}
