package com.seven.util;

import java.util.List;

import com.seven.entity.Course;
import com.seven.entity.Learning;

public class Result {
	
	private int result;
	private int studentId;
	private String studentNo;
	private String studentName;
	private String studentSex;
	private int studentAge;
	private String studentPassword;
	
	private int numberOfJob1;
	private int numberOfJob2;
	private int numberOfJob3;
	
	private double salaryOfJob1;
	private double salaryOfJob2;
	private double salaryOfJob3;
	
	private List<Course> courseInfo;
	private List<Learning> learningsInfo;
	
	private List<String> courseNo;
	private List<String> courseName;
	private List<String> teacherName;
	private List<Integer> courseScore;
	
	private List<String> studentNos;
	private List<String> studentNames;
	private List<Integer> courseAchieve;
	
	private List<Integer> totalScores;
	
	private double avgOfScore;
	private double totalScore;

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getStudentId() {
		return studentId;
	}
	public List<String> getCourseName() {
		return courseName;
	}
	public void setCourseName(List<String> courseName) {
		this.courseName = courseName;
	}
	public List<String> getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(List<String> teacherName) {
		this.teacherName = teacherName;
	}
	public List<Integer> getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(List<Integer> courseScore) {
		this.courseScore = courseScore;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public int getNumberOfJob1() {
		return numberOfJob1;
	}
	public void setNumberOfJob1(int numberOfJob1) {
		this.numberOfJob1 = numberOfJob1;
	}
	public int getNumberOfJob2() {
		return numberOfJob2;
	}
	public void setNumberOfJob2(int numberOfJob2) {
		this.numberOfJob2 = numberOfJob2;
	}
	public int getNumberOfJob3() {
		return numberOfJob3;
	}
	public void setNumberOfJob3(int numberOfJob3) {
		this.numberOfJob3 = numberOfJob3;
	}
	public double getSalaryOfJob1() {
		return salaryOfJob1;
	}
	public void setSalaryOfJob1(double salaryOfJob1) {
		this.salaryOfJob1 = salaryOfJob1;
	}
	public double getSalaryOfJob2() {
		return salaryOfJob2;
	}
	public void setSalaryOfJob2(double salaryOfJob2) {
		this.salaryOfJob2 = salaryOfJob2;
	}
	public double getSalaryOfJob3() {
		return salaryOfJob3;
	}
	public void setSalaryOfJob3(double salaryOfJob3) {
		this.salaryOfJob3 = salaryOfJob3;
	}
	public List<Course> getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(List<Course> courseInfo) {
		this.courseInfo = courseInfo;
	}
	public List<Learning> getLearningsInfo() {
		return learningsInfo;
	}
	public void setLearningsInfo(List<Learning> learningsInfo) {
		this.learningsInfo = learningsInfo;
	}
	public List<String> getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(List<String> courseNo) {
		this.courseNo = courseNo;
	}
	public double getAvgOfScore() {
		return avgOfScore;
	}
	public void setAvgOfScore(double avgOfScore) {
		this.avgOfScore = avgOfScore;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public List<String> getStudentNos() {
		return studentNos;
	}
	public void setStudentNos(List<String> studentNos) {
		this.studentNos = studentNos;
	}
	public List<String> getStudentNames() {
		return studentNames;
	}
	public void setStudentNames(List<String> studentNames) {
		this.studentNames = studentNames;
	}
	public List<Integer> getCourseAchieve() {
		return courseAchieve;
	}
	public void setCourseAchieve(List<Integer> courseAchieve) {
		this.courseAchieve = courseAchieve;
	}
	public List<Integer> getTotalScores() {
		return totalScores;
	}
	public void setTotalScores(List<Integer> totalScores) {
		this.totalScores = totalScores;
	}

	
}
