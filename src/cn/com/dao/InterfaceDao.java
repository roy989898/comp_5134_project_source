package cn.com.dao;

import java.util.List;

import cn.com.entity.Student;  
    
public interface InterfaceDao {  
    public boolean login(String name,String pwd);//��¼  
    public boolean update(int id,String chance, String days,String time) ;//�����û���Ϣ  
    public boolean insertCourse(int studentId, int teacherId,String teacherName , String teacherCourse);  //ѡ��
    public Student getStudentInfo(String name); //��ȡѧ����Ϣ
    public List<String> getCourses(int studentid); //ѧ����ѯѡ����Щ�γ�
    public List<String> getTeachers(String teachername);
}  