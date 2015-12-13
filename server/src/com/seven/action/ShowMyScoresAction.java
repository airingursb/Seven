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

public class ShowMyScoresAction extends ActionSupport implements ModelDriven<Student> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1345117142489748900L;
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
		Set<Learning> set = new HashSet<Learning>();
		
		set = studentDao.showMyCourse(student.getStudentId());
		List<Learning> list = new ArrayList<Learning>(set);
		int totalScore = 0;
		for (int i = 0; i < list.size(); i++) {
			Learning learning = (Learning) list.get(i); 
			int courseId = courseDao.getCourseIdByLearningId(learning.getLearningId());
			Course course = courseDao.getCourse(courseId);
			totalScore += course.getCourseScore();
		}
		//double avgOfScore = totalScore / list.size();
		result.setResult(1);
		result.setTotalScore(totalScore);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Student getModel() {
		return student;
	}

	
}
