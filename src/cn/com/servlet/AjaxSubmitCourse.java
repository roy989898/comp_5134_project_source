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

public class AjaxSubmitCourse extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法  
	    public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method  
	    }  
	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	          
	        int studentId = Integer.parseInt(request.getParameter("studentId"));  //得到页面传过来的参数  
	        String studentName = request.getParameter("studentName");
	        String courses = request.getParameter("courses");  
	        String chance = request.getParameter("chance");  //充值的次数
	        InterfaceDao dao = new DaoImpl();  
	        List<String> courseList = null;
	        if(courses !=null){
	        	courseList = Arrays.asList(courses.split(","));
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String DateStr = sdf.format(new Date());
	        try {
	        	//判断用户选课的权利
	        	Student student = dao.getStudentInfo(studentName);
	        	if(chance != null){  //充值功能
	        		int newChance = Integer.parseInt(student.getChance()) + Integer.parseInt(chance);
	        		dao.update(studentId, null, 90+"", DateStr);
	        		dao.update(studentId, newChance+"", null, null);
	        		PrintWriter out = response.getWriter();
			        out.print("succeed!");   // 若选择的课程比剩余的次数多
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
			        out.print("sorry, your chance is not enough!");   // 若选择的课程比剩余的次数多
					out.close();
				}else{
					//将选的课程存入数据库
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
			        dao.update(studentId, newChance+"", null, null);  //更新减少后的次数 
			        
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
