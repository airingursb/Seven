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
import com.opensymphony.xwork2.ModelDriven;
import com.seven.dao.CourseDao;
import com.seven.dao.CourseDaoImpl;
import com.seven.dao.StudentDao;
import com.seven.dao.StudentDaoImpl;
import com.seven.entity.Course;
import com.seven.entity.Learning;
import com.seven.entity.Student;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class ShowMyCourseAction extends ActionSupport implements ModelDriven<Student> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -435337937458027209L;
	private Student student = new Student();
	private Result result = new Result();
	
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
		Set<Learning> ls = new HashSet<Learning>();

		ls = studentDao.showMyCourse(student.getStudentId());
		List<Learning> list = new ArrayList<Learning>(ls);
		List<Course> courseList = new ArrayList<Course>();
		List<String> courseNos = new ArrayList<String>();
		List<String> courseNames = new ArrayList<String>();
		List<String> teacherNames = new ArrayList<String>();
		List<Integer> courseScores = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.size());
			Learning learning = (Learning) list.get(i); 
			int courseId = courseDao.getCourseIdByLearningId(learning.getLearningId());
			Course course = courseDao.getCourse(courseId);
			course.setCourseNo(learning.getCourse().getCourseNo());
			courseList.add(course);
			courseNos.add(course.getCourseNo());
			courseNames.add(course.getCourseName());
			teacherNames.add(course.getTeacher().getTeacherName());
			courseScores.add(course.getCourseScore());
		}

		System.out.println(courseList);
		result.setResult(1);
		result.setCourseNo(courseNos);
		result.setCourseName(courseNames);
		result.setTeacherName(teacherNames);
		result.setCourseScore(courseScores);
		//result.setCourseInfo(courseList);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Student getModel() {
		return student;
	}

}
