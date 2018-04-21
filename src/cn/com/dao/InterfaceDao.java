package cn.com.dao;

import java.util.List;

import cn.com.entity.Student;  
    
public interface InterfaceDao {  
    public boolean login(String name,String pwd);//登录  
    public boolean update(int id,String chance, String days,String time) ;//更新用户信息  
    public boolean insertCourse(int studentId, int teacherId,String teacherName , String teacherCourse);  //选课
    public Student getStudentInfo(String name); //获取学生信息
    public List<String> getCourses(int studentid); //学生查询选了哪些课程
    public List<String> getTeachers(String teachername);
}  