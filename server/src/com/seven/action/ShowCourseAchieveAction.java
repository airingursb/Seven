package com.seven.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.seven.entity.Course;
import com.seven.entity.Learning;
import com.seven.entity.Student;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class ShowCourseAchieveAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 820085574905568374L;
	private Result result = new Result();
	private String courseNo;
	
	public String execute() throws IOException {
		Gson gson = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.create();
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);  
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		StudentDao studentDao = new StudentDaoImpl();
		CourseDao courseDao = new CourseDaoImpl();
		LearningDao learningDao = new LearningDaoImpl();
		
		Set<Learning> ls = new HashSet<Learning>();
		int courseId = courseDao.getCourseIdByNo(courseNo);
		ls = learningDao.getLearningByCourseId(courseId);
		List<Learning> list = new ArrayList<Learning>(ls);
		List<String> courseNames = new ArrayList<String>();
		List<String> studentNos = new ArrayList<String>();
		List<String> studentNames = new ArrayList<String>();
		List<Integer> courseAchieve = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++){
			Learning learning = (Learning) list.get(i); 
			int studentId = studentDao.getStudentIdByLearningId(learning.getLearningId());
			Student student = studentDao.getStudent(studentId);
			Course course = courseDao.getCourse(courseId);
			courseNames.add(course.getCourseName());
			studentNos.add(student.getStudentNo());
			studentNames.add(student.getStudentName());
			courseAchieve.add(learning.getCourseAchieve());
		}
		
		result.setResult(1);
		result.setCourseName(courseNames);
		result.setStudentNos(studentNos);
		result.setStudentNames(studentNames);
		result.setCourseAchieve(courseAchieve);
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
}
