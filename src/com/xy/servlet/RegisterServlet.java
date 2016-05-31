package com.xy.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.XMLUtils;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("connection.....");
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String[] user = (String[]) objIn.readObject();
			// �������û���������
			String name = user[0];
			String password = user[1];
			// ��֤���û����Ƿ�ʹ��
			boolean isUsed = XMLUtils.isExist(name);
			Boolean result = false;
			// ����Ѿ���ʹ��
			if(isUsed){
				result  = false;
			} else {
				// ���û�б�ʹ��
				// ���µ��û���������д���ļ�
				// ��result��Ϊtrue
				XMLUtils.addUser(name, password);
				result = true;
			}
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			// ����һ��Boolean���󵽿ͻ���
			objOut.writeObject(new Boolean(result));
			objOut.flush();
			objOut.close();
			objIn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
