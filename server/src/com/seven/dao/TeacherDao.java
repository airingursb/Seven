package com.seven.dao;

import com.seven.entity.Teacher;

public interface TeacherDao {
	//获得教师信息
	public Teacher getTeacher(int teacherId);
	public boolean getTeacherByNo(String teacherNo);
	public int getTeacherIdByNo(String teacherNo);
	
	//添加教师信息
	public void saveTeacher(Teacher teacher);
	
	//删除教师信息
	public void deleteTeacher(int teacherId);
	
	//修改教师信息
	public void updateTeacherInfo(int teacherId, String teacherName, String teacherJob, Double teacherSalary);

	//获得教师职称信息
	public int showNumberOfTeacherJob(String teacherJob);
	public double showSalaryOfTeacherJob(String teacherJob);
}
