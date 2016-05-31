package com.xy.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class IndexUtils {
	/**
	 * ����һ��ArrayList<String []>���������ļ��� String[],element:FileName, FileSize,
	 * Map<String[],Bitmap>
	 * UserName, UploadTime
	 * 
	 * @throws Exception
	 */
	@Test
	public void createArr() throws Exception {
		ArrayList<String[]> listData = new ArrayList<String[]>();
		String[] cell1 = new String[] { "1213.mp4", "32M", "2016-5-12" };
		String[] cell2 = new String[] { "1215.mp4", "32M", "2016-5-12" };
		listData.add(cell1);
		listData.add(cell2);
		ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream(new File("D:\\BSData\\listData.obj")));
		objOut.writeObject(listData);
		objOut.flush();
		objOut.close();
	}

	/**
	 * ɾ���ظ���¼
	 * 
	 */
	public static ArrayList<HashMap<String[], String>> trimListData(ArrayList<HashMap<String[], String>> listData){
		System.out.println("IndexUtils>trimArray>listData.size():"+listData.size());
		for (int i = 0; i < listData.size(); i++) {
			// �õ���ǰ��HashMap
			HashMap<String[], String> temp = listData.get(i);
			String[] info = temp.keySet().iterator().next();
			// �õ��ļ������û���
			String fileName = info[0];
			String user = info[3];
			// �ӵ�ǰλ�õ���һ��λ�ÿ�ʼ����
			for (int j = i+1; j < listData.size(); j++) {
				// �õ���ǰ��HashMap
				HashMap<String[], String> temp2 = listData.get(j);
				String[] info2 = temp2.keySet().iterator().next();
				// �õ��ļ������û���
				String fileName2 = info2[0];
				String user2 = info2[3];
				// �����ж�,�����ͬ,��ɾ��ǰ���,�������µ�
				if(fileName.equals(fileName2) && user.equals(user2)){
					listData.remove(i);
					i--;
					break;
				}
			}
		}
		return listData;
	}
	/**
	 * ���listData
	 * @param listData
	 * @return
	 */
	public static ArrayList<HashMap<String[], String>> clearListData(ArrayList<HashMap<String[], String>> listData){
		for (int i = 1; i < listData.size(); i++) {
			listData.remove(i);
		}
		return listData;
	}
}
