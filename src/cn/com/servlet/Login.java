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
  
public class Login extends HttpServlet {  //��Ҫ�̳�HttpServlet  ����дdoGet  doPost����  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doPost(request, response);  //����Ϣʹ��doPost����ִ��   ��Ӧjspҳ���е�form���е�method  
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
          
        String name = request.getParameter("name"); //�õ�jspҳ�洫�����Ĳ���  
        String pwd = request.getParameter("pwd");  
          
        InterfaceDao dao = new DaoImpl();  
        
        if (name != null && pwd !=null ) {   // ��ʦ��½
        	List<String> stuIdList = null;
        	String mycourse = null;
			if(name.equals("teacher1")&&pwd.equals("teacher1")){
				stuIdList = dao.getTeachers(name);
				mycourse = "course1";
				request.setAttribute("message", "Welcome  "+name + " !"); //��request���з�����Ϣ  
	            request.setAttribute("name", name);
	            request.setAttribute("stuIdList", stuIdList);
	            request.setAttribute("mycourse", mycourse);
	            request.getRequestDispatcher("/teacher.jsp").forward(request, response);//ת�����ɹ�ҳ��  
			}else if(name.equals("teacher2")&&pwd.equals("teacher2")){
				stuIdList = dao.getTeachers(name);
				mycourse = "course2";
				request.setAttribute("message", "Welcome  "+name + " !"); //��request���з�����Ϣ  
	            request.setAttribute("name", name);
	            request.setAttribute("stuIdList", stuIdList);
	            request.setAttribute("mycourse", mycourse);
	            request.getRequestDispatcher("/teacher.jsp").forward(request, response);//ת�����ɹ�ҳ��  
			}else if(dao.login(name, pwd)){  
        	Student student = dao.getStudentInfo(name);
        	if(student != null){
        		List<String> list = dao.getCourses(student.getId());   //ѧ����ѡ�γ�list
        		StringBuffer courseStr = new StringBuffer();   // �洢ѧ���Ѿ�ѡ�Ŀγ�
        		for (int i=0; i<list.size(); i++) {
        			courseStr.append(list.get(i));
        			if(i<list.size()-1){
        				courseStr.append(",");    //�ö��Ÿ���
        			}
				}
        		//�õ�����ѡ��Ŀγ��б�
        		List<String> ableList= new ArrayList<String>();
        		for (int i=1; i<=30; i++) {
        			ableList.add("course"+i);    //��30���γ�
        		}
        		for (int i=0; i<list.size(); i++) {       //��ѡ�Ŀγ̲������ڴ�ѡ�Ŀγ���
        			for(int j=0; j<ableList.size();j++){
        				if (ableList.get(j).equals(list.get(i))) { 
        					ableList.remove(j); 
        					j--;
        				}
        			}
        		}
        		
        		request.setAttribute("message", "Welcome  "+name + " !"); //��request���з�����Ϣ  
                request.setAttribute("name", student.getName());
                request.setAttribute("studentId", student.getId());
                request.setAttribute("chance", student.getChance());
                request.setAttribute("days", student.getDays());
                request.setAttribute("courseStr", courseStr);
                request.setAttribute("ableList", ableList);
                request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);//ת�����ɹ�ҳ��  
        	}
        }
    }else{  
            response.sendRedirect("login.jsp"); //�ض�����ҳ  
        }  
    }  
}
  