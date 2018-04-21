package cn.com.servlet;

import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import cn.com.dao.DaoImpl;
import cn.com.dao.InterfaceDao;
import cn.com.entity.Student;  
  
public class Login extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method  
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
          
        String name = request.getParameter("name"); //得到jsp页面传过来的参数  
        String pwd = request.getParameter("pwd");  
          
        InterfaceDao dao = new DaoImpl();  
        
        if (name != null && pwd !=null ) {   // 老师登陆
        	List<String> stuIdList = null;
        	String mycourse = null;
			if(name.equals("teacher1")&&pwd.equals("teacher1")){
				stuIdList = dao.getTeachers(name);
				mycourse = "course1";
				request.setAttribute("message", "Welcome  "+name + " !"); //向request域中放置信息  
	            request.setAttribute("name", name);
	            request.setAttribute("stuIdList", stuIdList);
	            request.setAttribute("mycourse", mycourse);
	            request.getRequestDispatcher("/teacher.jsp").forward(request, response);//转发到成功页面  
			}else if(name.equals("teacher2")&&pwd.equals("teacher2")){
				stuIdList = dao.getTeachers(name);
				mycourse = "course2";
				request.setAttribute("message", "Welcome  "+name + " !"); //向request域中放置信息  
	            request.setAttribute("name", name);
	            request.setAttribute("stuIdList", stuIdList);
	            request.setAttribute("mycourse", mycourse);
	            request.getRequestDispatcher("/teacher.jsp").forward(request, response);//转发到成功页面  
			}else if(dao.login(name, pwd)){  
        	Student student = dao.getStudentInfo(name);
        	if(student != null){
        		List<String> list = dao.getCourses(student.getId());   //学生已选课程list
        		StringBuffer courseStr = new StringBuffer();   // 存储学生已经选的课程
        		for (int i=0; i<list.size(); i++) {
        			courseStr.append(list.get(i));
        			if(i<list.size()-1){
        				courseStr.append(",");    //用逗号隔开
        			}
				}
        		//得到可以选择的课程列表
        		List<String> ableList= new ArrayList<String>();
        		for (int i=1; i<=30; i++) {
        			ableList.add("course"+i);    //共30个课程
        		}
        		for (int i=0; i<list.size(); i++) {       //已选的课程不出现在待选的课程中
        			for(int j=0; j<ableList.size();j++){
        				if (ableList.get(j).equals(list.get(i))) { 
        					ableList.remove(j); 
        					j--;
        				}
        			}
        		}
        		
        		request.setAttribute("message", "Welcome  "+name + " !"); //向request域中放置信息  
                request.setAttribute("name", student.getName());
                request.setAttribute("studentId", student.getId());
                request.setAttribute("chance", student.getChance());
                request.setAttribute("days", student.getDays());
                request.setAttribute("courseStr", courseStr);
                request.setAttribute("ableList", ableList);
                request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);//转发到成功页面  
        	}
        }
    }else{  
            response.sendRedirect("login.jsp"); //重定向到首页  
        }  
    }  
}
  