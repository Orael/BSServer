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
import com.xy.utils.IndexUtils;

/**
 * ��Ӧ�ֻ��˵������ϴ���ʷ�ļ��µĵ�����,�����û����ϴ���ʷ�ļ���Ϣ�Ķ���������������
 * @author Administrator
 *
 */
public class GetListViewDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("���˷���getListViewDataServlet");
			// ���ܵ�listDataѡ�����û��ļ�¼,�洢
			ArrayList<HashMap<String[],String>> listDataForClient = new ArrayList<HashMap<String[],String>>();
			// �õ�Ҫ���ҵ��û���
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String userName = (String) objIn.readObject();
			System.out.println("userName::::" + userName);
			objIn.close();
			// �ӱ����ļ���ȡ���洢���ݵ�listData.boj
			ArrayList<HashMap<String[],String>> listData2 = (ArrayList<HashMap<String[],String>>) DiskUtils.getListData();
			// ���ظ��ϴ��򽫾ɵļ�¼ɾ��
			ArrayList<HashMap<String[],String>> listData = IndexUtils.trimListData(listData2);
			// ����listData,�ҵ����û����ϴ���¼
			for (int i = 0; i < listData.size(); i++) {
				HashMap<String[], String> hm = listData.get(i);
				String hm_userName = hm.keySet().iterator().next()[3];
				if (hm_userName.equals(userName)) {
					listDataForClient.add(hm);
				}
			}
			// �������ļ�д�������������client
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(listDataForClient);
			objOut.flush();
			objOut.close();
			objIn.close();
			// ���µ�lsitDataд�뵽Ӳ����
			DiskUtils.writeToDisk(listData);
			
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
