package com.seven.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer courseId;
	private Teacher teacher;
	private String courseNo;
	private Integer courseScore;
	private String courseName;
	private Set learnings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Teacher teacher, String courseNo, Integer courseScore,
			String courseName) {
		this.teacher = teacher;
		this.courseNo = courseNo;
		this.courseScore = courseScore;
		this.courseName = courseName;
	}

	/** full constructor */
	public Course(Teacher teacher, String courseNo, Integer courseScore,
			String courseName, Set learnings) {
		this.teacher = teacher;
		this.courseNo = courseNo;
		this.courseScore = courseScore;
		this.courseName = courseName;
		this.learnings = learnings;
	}

	// Property accessors

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public Integer getCourseScore() {
		return this.courseScore;
	}

	public void setCourseScore(Integer courseScore) {
		this.courseScore = courseScore;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set getLearnings() {
		return this.learnings;
	}

	public void setLearnings(Set learnings) {
		this.learnings = learnings;
	}

}