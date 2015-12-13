package com.seven.dao;

import com.seven.entity.Course;
import com.seven.entity.Teacher;

public interface CourseDao {
	//获得课程信息
	public Course getCourse(int courseId);
	public boolean getCourseByNo(String courseNo);
	public int getCourseIdByNo(String courseNo);
	public int getCourseIdByLearningId(int learningId);
	
	//添加课程信息
	public void saveCourse(Course course);
	
	//删除课程信息
	public void deleteCourse(int courseId);
	
	//修改课程信息	
	public void updateCourseInfo(int courseId, Teacher teacher, 
			String courseNo, String courseName, int courseScore);

}
