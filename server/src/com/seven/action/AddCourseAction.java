package com.seven.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.seven.dao.CourseDao;
import com.seven.dao.CourseDaoImpl;
import com.seven.dao.TeacherDao;
import com.seven.dao.TeacherDaoImpl;
import com.seven.entity.Course;
import com.seven.entity.Teacher;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class AddCourseAction extends ActionSupport implements ModelDriven<Course> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5341772221824334599L;
	private Course course = new Course();
	private Result result = new Result();
	private String teacherNo;
	
	public String execute() throws IOException {
		Gson gson = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.create();
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);  
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		CourseDao courseDao = new CourseDaoImpl();
		TeacherDao teacherDao = new TeacherDaoImpl();
		int teacherId = teacherDao.getTeacherIdByNo(teacherNo);
		Teacher teacher = teacherDao.getTeacher(teacherId);
		course.setTeacher(teacher);
		courseDao.saveCourse(course);
		
		result.setResult(1);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Course getModel() {
		return course;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

}
