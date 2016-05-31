package com.xy.servlet;

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

public class ReceiveFileInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("���˷���servlet/ReceiveFileInfoServlet>>>>>>");
			// ���տͻ��˷������ı����ϴ��ļ�����Ϣ
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String[], String>> singleFileInfo = (ArrayList<HashMap<String[], String>>) objIn.readObject();
			
			// ��Ӳ�̵õ��������˴洢�ļ���Ϣ�Ķ���
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String[], String>> listData = (ArrayList<HashMap<String[], String>>) DiskUtils.getListData();
			System.out.println("�ڴ�Ӳ�̶�ȡlistData֮��>>>>>>>");
			// �������Ϊ0,�򴴽�һ��
			if (listData.size() == 0) {
				System.out.println("�ж�����Ӳ�̵�listData�����Ƿ�Ϊ0>>>>>>��Ϊ0");
				listData = new ArrayList<HashMap<String[],String>>();
			}
			System.out.println("listData��Ϊ0,���ӡ����:"+listData.size());
			// �����ϴ��ļ�����Ϣ��ӵ�listData
			listData.addAll(singleFileInfo);
			// ��listData����д��Ӳ��
			DiskUtils.writeToDisk(listData);
			// ��ִ
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(new String("finish"));
			objOut.flush();
			// �ر���
			objOut.close();
			objIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
