package com.xy.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MediaFileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 5L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// �õ������ļ����ļ������û���
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String userName_fileName = (String) objIn.readObject();
			// �����ж�,��Ȼ�Ѿ���������,֤��֮ǰһ���ϴ���,����һ�������û�Ŀ¼
			File file = new File("D:\\BSData\\" + userName_fileName);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			// ���ļ�д��
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			// �ر���
			bos.close();
			objIn.close();
			bis.close();

		} catch (Exception e) {

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
