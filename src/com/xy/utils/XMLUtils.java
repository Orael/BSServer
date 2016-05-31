package com.xy.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ͨ��������name��������֮ƥ���password
 * 
 * @author Administrator
 * 
 */
public class XMLUtils {
	
	@Test
	public void test2(){
	}
	/**
	 * MD5�����㷨
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5Digest(String password) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		// 1. ��ȡժҪ��
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		// 2.�õ������ժҪ Ϊbyte����
		byte[] digest = messageDigest.digest(password.getBytes());
		// 3.��������
		for (int i = 0; i < digest.length; i++) {
			// 4.MD5����
			int result = digest[i] & 0xff;
			// ת��Ϊ16����
			String hexString = Integer.toHexString(result) + 1;
			if(hexString.length() < 2){
				sb.append("0");
			}
			sb.append(hexString);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * ��¼��֤
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static boolean loginCheck(String name, String password)
			throws Exception {
		// �����¶���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// �õ�document����
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// �õ�Ҫ���ҵ�user�ڵ�
		NodeList userList = document.getElementsByTagName("user");
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			String s_name = user.getElementsByTagName("name").item(0)
					.getTextContent();
			System.out.println("��ǰname>>>" + s_name);
			// �����ǰ�ڵ���Ҫ���ҵĽڵ�
			if (s_name.equals(name)) {
				String s_pass = user.getElementsByTagName("password").item(0)
						.getTextContent();
				// ���������ȷ
				if (s_pass.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * �����û����õ�����
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String getPassword(String name)
			throws ParserConfigurationException, SAXException, IOException {
		// �����¶���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// �õ�document����
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// �õ�Ҫ���ҵ�user�б�
		NodeList userList = document.getElementsByTagName("user");
		// �����б���ÿһ��user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// �õ�user�µ�name��ǩ
			// getElementsByTagName �� ����������Ϊname��list
			// getTextContent �� �õ���ǩ������
			String s_name = user.getElementsByTagName("name").item(0)
					.getTextContent();
			System.out.println("��ǰname>>>" + s_name);
			// �����ǰuser��Ҫ���ҵ�user
			if (s_name.equals(name)) {
				// �õ����user��password������
				String s_pass = user.getElementsByTagName("password").item(0)
						.getTextContent();
				return s_pass;
			}
		}
		return null;
	}

	/**
	 * �ж�����û����Ƿ����
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static boolean isExist(String name)
			throws ParserConfigurationException, SAXException, IOException {
		// �����¶���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// �õ�document����
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// �õ�Ҫ���ҵ�user�б�
		NodeList userList = document.getElementsByTagName("user");
		// �����б���ÿһ��user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// �õ�user�µ�name��ǩ
			// getElementsByTagName �� ����������Ϊname��list
			// getTextContent �� �õ���ǩ������
			String s_name = user.getElementsByTagName("name").item(0).getTextContent();
			System.out.println("��ǰname>>>" + s_name);
			// �����ǰuser��Ҫ���ҵ�user
			if (s_name.equals(name)) {
				// �������
				return true;
			}
		}
		return false;
	}
	/**
	 * ɾ���û�
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException 
	 */
	public static boolean deleteUser(String name)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// �����¶���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// �õ�document����
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// �õ�Ҫ���ҵ�user�б�
		NodeList userList = document.getElementsByTagName("user");
		// �����б���ÿһ��user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// �õ�user�µ�name��ǩ
			// getElementsByTagName �� ����������Ϊname��list
			// getTextContent �� �õ���ǩ������
			String s_name = user.getElementsByTagName("name").item(0).getTextContent();
			System.out.println("��ǰname>>>" + s_name);
			// �����ǰuser��Ҫ���ҵ�user
			if (s_name.equals(name)) {
				// �������
				user.getParentNode().removeChild(user);
				return true;
			}
		}
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("BSServer/users.xml")));

		return false;
	}

	/**
	 * ����û�
	 * @param name �û���
	 * @param password ����
	 * @return
	 * @throws Exception
	 */
	public static boolean addUser(String name, String password)
			throws Exception {
		// �����¶���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// �õ�document����
		// Document document = builder.parse(new File("users.xml"));
		Document document = builder.parse("BSServer/users.xml");

		Node user = document.createElement("user");
		Element nameEle = document.createElement("name");
		nameEle.setTextContent(name);
		Element passEle = document.createElement("password");
		passEle.setTextContent(password);
		user.appendChild(nameEle);
		user.appendChild(passEle);

		Node users = document.getElementsByTagName("users").item(0);
		users.appendChild(user);

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("BSServer/users.xml")));
//		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("users.xml")));

		return true;
	}

	@Test
	public void test1() {
		try {
			XMLUtils.addUser("li", "jfj");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
