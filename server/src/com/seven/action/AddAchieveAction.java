package com.seven.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.seven.dao.CourseDao;
import com.seven.dao.CourseDaoImpl;
import com.seven.dao.LearningDao;
import com.seven.dao.LearningDaoImpl;
import com.seven.dao.StudentDao;
import com.seven.dao.StudentDaoImpl;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class AddAchieveAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2847249219148455424L;
	private Result result = new Result();
	
	private String courseNo;
	private String studentNo;
	private int courseAchieve;
	
	public String execute() throws IOException {
		Gson gson = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.create();
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);  
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		LearningDao learningDao = new LearningDaoImpl();
		StudentDao studentDao = new StudentDaoImpl();
		CourseDao courseDao = new CourseDaoImpl();
		
		int studentId = studentDao.getStudentIdByNo(studentNo);
		int courseId = courseDao.getCourseIdByNo(courseNo);
		int learningId = learningDao.getLearningId(courseId, studentId);
		System.out.println("studentId:"+studentId);
		System.out.println("courseId:"+courseId);
		System.out.println("learningId:"+learningId);
		System.out.println("courseAchieve:"+courseAchieve);
		learningDao.addAchieve(learningId, courseAchieve);
		
		result.setResult(1);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public int getCourseAchieve() {
		return courseAchieve;
	}

	public void setCourseAchieve(int courseAchieve) {
		this.courseAchieve = courseAchieve;
	}

	
}
