package com.seven.dao;

import java.util.Set;

import com.seven.entity.Course;
import com.seven.entity.Learning;
import com.seven.entity.Student;

public interface StudentDao {
	//获得学生信息
	public Student getStudent(int studentId);
	public boolean getStudentByNo(String studentNo);
	public int getStudentIdByNo(String studentNo);
	public int getStudentIdByLearningId(int learningId);
	public Set<Student> getAllStudents();
	
	//添加学生信息
	public void saveStudent(Student student);
	
	//删除学生信息
	public void deleteStudent(int studentId);
	
	//修改学生信息	
	public void updateStudentInfo(int studentId, String studentPassword, String studentName, String studentSex, int studentAge);
	public void updateStudentPassword(int studentId, String studentPassword);
	public void updateStudentName(int studentId, String studentName);
	public void updateStudentSex(int studentId, String studentSex);
	public void updateStudentAge(int studentId, int studentAge);

	//相关业务
	public boolean studentLogin(Student student);
	public void selectCourse(Student student, Course course);
	public Set<Learning> showMyCourse(int studentId);
}
