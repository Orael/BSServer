package com.xy.servlet;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.DiskUtils;

public class DeleteFileServlet extends HttpServlet {

	private static final long serialVersionUID = 9L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean isDelete = null ;
		try {
			// �õ��û�Ŀ¼+�ļ���
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String userName_fileName = (String) objIn.readObject();
			String userName = userName_fileName.split("\\\\")[0];
			String fileName = userName_fileName.split("\\\\")[1];
			// ����Ҫɾ�����ļ�
			File file =new File("D:\\BSData\\" + userName_fileName);
			if (file.exists()) {
				System.out.println("file.exists()>>>>>"+file.exists());
				System.gc();
				isDelete = file.delete();
				System.out.println("isDelete  File....>>>>>"+isDelete);
			}
			// ��Ӳ�̵õ��������˴洢�ļ���Ϣ�Ķ���
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String[], String>> listData = (ArrayList<HashMap<String[], String>>) DiskUtils.getListData();
			// ��������List,ɾ�������Ǽ�¼
			for (int i = 0; i < listData.size(); i++) {
				HashMap<String[], String> temp = listData.get(i);
				String[] strings = temp.keySet().iterator().next();
				String fileName2 = strings[0];
				String userName2 = strings[3];
				// �ж��Ƿ���ͬ, ��ͬ��ɾ��
				if(fileName.equals(fileName2) && userName.equals(userName2)){
					listData.remove(i);
					i--;
				}
			}
			// ��listData����д��Ӳ��
			DiskUtils.writeToDisk(listData);
			// ��ɾ���������ֵ���͵��ͻ���
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(isDelete);
			objOut.flush();
			objOut.close();
			
			objIn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
