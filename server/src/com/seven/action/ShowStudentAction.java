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
import com.seven.dao.StudentDao;
import com.seven.dao.StudentDaoImpl;
import com.seven.entity.Student;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class ShowStudentAction extends ActionSupport implements ModelDriven<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572062493936408425L;
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
		Student studentInfo = studentDao.getStudent(student.getStudentId());
		result.setResult(1);
		result.setStudentId(studentInfo.getStudentId());
		result.setStudentNo(studentInfo.getStudentNo());
		result.setStudentName(studentInfo.getStudentName());
		result.setStudentSex(studentInfo.getStudentSex());
		result.setStudentAge(studentInfo.getStudentAge());
		result.setStudentPassword(studentInfo.getStudentPassword());

		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Student getModel() {
		return student;
	}

}
