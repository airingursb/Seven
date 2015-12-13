package com.seven.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer teacherId;
	private String teacherNo;
	private String teacherName;
	private String teacherJob;
	private Double teacherSalary;
	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String teacherNo, String teacherName, String teacherJob,
			Double teacherSalary) {
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.teacherJob = teacherJob;
		this.teacherSalary = teacherSalary;
	}

	/** full constructor */
	public Teacher(String teacherNo, String teacherName, String teacherJob,
			Double teacherSalary, Set courses) {
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.teacherJob = teacherJob;
		this.teacherSalary = teacherSalary;
		this.courses = courses;
	}

	// Property accessors

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherNo() {
		return this.teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherJob() {
		return this.teacherJob;
	}

	public void setTeacherJob(String teacherJob) {
		this.teacherJob = teacherJob;
	}

	public Double getTeacherSalary() {
		return this.teacherSalary;
	}

	public void setTeacherSalary(Double teacherSalary) {
		this.teacherSalary = teacherSalary;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

}