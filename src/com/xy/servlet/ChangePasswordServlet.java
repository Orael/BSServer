package com.xy.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.xy.utils.XMLUtils;

public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 7L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			boolean isSuccess = false;
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			ArrayList<String> data = (ArrayList<String>) objIn.readObject();
			// �õ�"�û���" "������" "������"
			String userName = data.get(0);
			String oldPass = data.get(1);
			String newPass = data.get(2);
			// ����"�û���" ���Ҿ�����
			String oldPassFromXML = XMLUtils.getPassword(userName);
			// �жϾ������Ƿ���ȷ   �����ͬ,�����޸�
			if (oldPassFromXML.equals(oldPass)) {
				XMLUtils.deleteUser(userName);
				isSuccess = XMLUtils.addUser(userName, newPass);
			}
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(new Boolean(isSuccess));
			objOut.flush();
			objIn.close();
			objOut.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
