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

public class ShowStudentScoresAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1679785884823835985L;

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
		LearningDao learningDao = new LearningDaoImpl();
		
		Set<Student> set = new HashSet<Student>();
		set = studentDao.getAllStudents();
		List<Student> list = new ArrayList<Student>(set);	
		int totalScore = 0;
		List<String> studentNos = new ArrayList<String>();
		List<String> studentNames = new ArrayList<String>();
		List<Integer> totalScores = new ArrayList<Integer>();
		for(int i = 0; i < list.size(); i++) {
			totalScore = 0;
			Student student = (Student)list.get(i);
			int studentId = student.getStudentId();
			Set<Learning> learnings = new HashSet<Learning>();
			learnings = learningDao.getLearningByStudentId(studentId);
			List<Learning> learningList = new ArrayList<Learning>(learnings);
			for(int j = 0; j < learningList.size(); j++){
				Learning learning = (Learning)learningList.get(j);
				int courseId = courseDao.getCourseIdByLearningId(learning.getLearningId());
				Course course = courseDao.getCourse(courseId);
				totalScore += course.getCourseScore();
			}
			totalScores.add(totalScore);
			studentNos.add(student.getStudentNo());
			studentNames.add(student.getStudentName());
		}
		result.setResult(1);
		result.setStudentNos(studentNos);
		result.setStudentNames(studentNames);
		result.setTotalScores(totalScores);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
}
