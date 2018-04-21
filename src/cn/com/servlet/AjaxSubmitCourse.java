package cn.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.dao.DaoImpl;
import cn.com.dao.InterfaceDao;
import cn.com.entity.Student;

public class AjaxSubmitCourse extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����  
	    public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method  
	    }  
	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	          
	        int studentId = Integer.parseInt(request.getParameter("studentId"));  //�õ�ҳ�洫�����Ĳ���  
	        String studentName = request.getParameter("studentName");
	        String courses = request.getParameter("courses");  
	        String chance = request.getParameter("chance");  //��ֵ�Ĵ���
	        InterfaceDao dao = new DaoImpl();  
	        List<String> courseList = null;
	        if(courses !=null){
	        	courseList = Arrays.asList(courses.split(","));
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String DateStr = sdf.format(new Date());
	        try {
	        	//�ж��û�ѡ�ε�Ȩ��
	        	Student student = dao.getStudentInfo(studentName);
	        	if(chance != null){  //��ֵ����
	        		int newChance = Integer.parseInt(student.getChance()) + Integer.parseInt(chance);
	        		dao.update(studentId, null, 90+"", DateStr);
	        		dao.update(studentId, newChance+"", null, null);
	        		PrintWriter out = response.getWriter();
			        out.print("succeed!");   // ��ѡ��Ŀγ̱�ʣ��Ĵ�����
					out.close();
	        	}else if (Integer.parseInt(student.getChance()) <= 0 ) {
	        		PrintWriter out = response.getWriter();
			        out.print("sorry, your chance is 0!"); 
					out.close();
				}else if (Integer.parseInt(student.getDays()) <= 0) {
					PrintWriter out = response.getWriter();
			        out.print("sorry, your days is 0!"); 
					out.close();
				}else if(Integer.parseInt(student.getChance())-courseList.size() < 0){
					PrintWriter out = response.getWriter();
			        out.print("sorry, your chance is not enough!");   // ��ѡ��Ŀγ̱�ʣ��Ĵ�����
					out.close();
				}else{
					//��ѡ�Ŀγ̴������ݿ�
			        for (int i = 0; i < courseList.size(); i++) {
			        	
			        		String course = courseList.get(i);
			        		int teacherId = 0;
			        		if(course.length() == 7){
			        			teacherId = Integer.parseInt(course.substring(course.length()-1, course.length()));
			        		}else{
			        			teacherId = Integer.parseInt(course.substring(course.length()-2, course.length()));
			        		}
				        	dao.insertCourse(studentId, teacherId, "teacher" + teacherId, course);
						
					}
			        int newChance = Integer.parseInt(student.getChance())-courseList.size();
			        dao.update(studentId, newChance+"", null, null);  //���¼��ٺ�Ĵ��� 
			        
			        //response.setContentType("text/xml;charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.print("ok"); 
					out.close();
				}
	        	
	        } catch (Exception e) {
				e.printStackTrace();
			} 
	    }  
	}
