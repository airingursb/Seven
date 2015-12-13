package com.seven.entity;

/**
 * Learning entity. @author MyEclipse Persistence Tools
 */

public class Learning implements java.io.Serializable {

	// Fields

	private Integer learningId;
	private Student student;
	private Course course;
	private Integer courseAchieve;

	// Constructors

	/** default constructor */
	public Learning() {
	}

	/** full constructor */
	public Learning(Student student, Course course, Integer courseAchieve) {
		this.student = student;
		this.course = course;
		this.courseAchieve = courseAchieve;
	}

	// Property accessors

	public Integer getLearningId() {
		return this.learningId;
	}

	public void setLearningId(Integer learningId) {
		this.learningId = learningId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCourseAchieve() {
		return this.courseAchieve;
	}

	public void setCourseAchieve(Integer courseAchieve) {
		this.courseAchieve = courseAchieve;
	}

}