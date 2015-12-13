package com.seven.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer studentId;
	private String studentNo;
	private String studentName;
	private String studentSex;
	private Integer studentAge;
	private String studentPassword;
	private Set learnings = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String studentNo, String studentName, String studentSex,
			Integer studentAge, String studentPassword) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentSex = studentSex;
		this.studentAge = studentAge;
		this.studentPassword = studentPassword;
	}

	/** full constructor */
	public Student(String studentNo, String studentName, String studentSex,
			Integer studentAge, String studentPassword, Set learnings) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentSex = studentSex;
		this.studentAge = studentAge;
		this.studentPassword = studentPassword;
		this.learnings = learnings;
	}

	// Property accessors

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSex() {
		return this.studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public Integer getStudentAge() {
		return this.studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentPassword() {
		return this.studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public Set getLearnings() {
		return this.learnings;
	}

	public void setLearnings(Set learnings) {
		this.learnings = learnings;
	}

}