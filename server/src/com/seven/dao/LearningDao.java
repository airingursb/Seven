package com.seven.dao;

import java.util.Set;

import com.seven.entity.Learning;


public interface LearningDao {

	//获得选课信息
	public Learning getLearning(int learningId);
	public int getLearningId(int courseId, int studentId);
	public Set<Learning> getLearningByCourseId(int courseId);
	public Set<Learning> getLearningByStudentId(int studentId);

	//添加成绩
	public void addAchieve(int learningId, int courseAchieve);
	
	
	
	
}
