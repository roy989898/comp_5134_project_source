package cn.com.dao;

import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;

import cn.com.entity.Student;
import cn.com.util.DBconn;  
  
public class DaoImpl implements InterfaceDao{  
    public boolean login(String name, String pwd) {  
        boolean flag = false;  
        try {  
                DBconn.init();  
                ResultSet rs = DBconn.selectSql("select * from student where name='"+name+"' and pwd='"+pwd+"'");  
                while(rs.next()){  
                    if(rs.getString("name").equals(name) && rs.getString("pwd").equals(pwd)){  
                        flag = true;  
                    }  
                }  
                DBconn.closeConn();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
    
    public boolean update(int id, String chance, String days,String time) {  
        boolean flag = false;  
        DBconn.init();  
        String sql = null;
        if(days != null && time != null){
        	 sql ="update student set days ='"+days  
                     + "', time='" + time + "'where id = "+id;  
        }
        else if (chance != null) {
        	 sql ="update student set chance ='"+chance  
                     +"'where id = "+id;  
		}
        
        int i =DBconn.addUpdDel(sql);  
        if(i>0){  
            flag = true;  
        }  
        DBconn.closeConn();  
        return flag;  
    }  
    public boolean delete(int id) {  
        boolean flag = false;  
        DBconn.init();  
        String sql = "delete  from user where id="+id;  
        int i =DBconn.addUpdDel(sql);  
        if(i>0){  
            flag = true;  
        }  
        DBconn.closeConn();  
        return flag;  
    }
	@Override
	public boolean insertCourse(int studentId, int teacherId, String teacherName, String teacherCourse) {
		boolean flag = false;  
        DBconn.init();  
        String sql ="INSERT INTO choose_table (studentid, teacherid,teachername,teachercourse)VALUES(" + studentId  
                + ", " + teacherId  
                + ", '" + teacherName  
                + "' , '" + teacherCourse  
                + "')";  
        int i =DBconn.addUpdDel(sql);  
        if(i>0){  
            flag = true;  
        }  
        DBconn.closeConn();  
        return flag;  
	}

	@Override
	public Student getStudentInfo(String name) {
	    Student student = new Student();  
		try {  
	            DBconn.init();  
	            ResultSet rs = DBconn.selectSql("select * from student where name='" + name + "'");  
	            while(rs.next()){  
	            	student.setId(rs.getInt("id"));  
	            	student.setName(rs.getString("name")); 
	            	student.setChance(rs.getString("chance"));
	                student.setDays(rs.getString("days"));
	            }  
	            DBconn.closeConn();  
	            return student;  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	}

	@Override
	public List<String> getCourses(int studentid) {
		List<String> list = new ArrayList<String>();  
        try {  
            DBconn.init();  
            ResultSet rs = DBconn.selectSql("select teachercourse from choose_table where studentid = '" + studentid + "'");  
            while(rs.next()){  
                list.add(rs.getString("teachercourse"));  
            }  
            DBconn.closeConn();  
            return list;  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
	}

	@Override
	public List<String> getTeachers(String teachername) {
		List<String> list = new ArrayList<String>();  
        try {  
            DBconn.init();  
            ResultSet rs = DBconn.selectSql("select studentid from choose_table where teachername = '" + teachername + "'");  
            while(rs.next()){  
                list.add(rs.getString("studentid"));  
            }  
            DBconn.closeConn();  
            return list;  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
	}  
      
}  

