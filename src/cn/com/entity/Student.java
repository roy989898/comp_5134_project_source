/*
 * ѧ��ʵ����
 */
package cn.com.entity;

public class Student {
	private int id;    //id
	private String name;   //�˻�
	private String pwd;		//����
	private String chance;	//ʣ��ѡ�δ���
	private String days;	//ʣ����Ч����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getChance() {
		return chance;
	}
	public void setChance(String chance) {
		this.chance = chance;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
}
