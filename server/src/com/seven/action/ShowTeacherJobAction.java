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
import com.seven.dao.TeacherDao;
import com.seven.dao.TeacherDaoImpl;
import com.seven.entity.Teacher;
import com.seven.util.HibernateProxyTypeAdapter;
import com.seven.util.Result;

public class ShowTeacherJobAction extends ActionSupport implements
		ModelDriven<Teacher> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5684312279013068636L;
	private Teacher teacher = new Teacher();
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
		TeacherDao teacherDao = new TeacherDaoImpl();
		
		int numberOfJob1 = teacherDao.showNumberOfTeacherJob("教授");
		int numberOfJob2 = teacherDao.showNumberOfTeacherJob("副教授");
		int numberOfJob3 = teacherDao.showNumberOfTeacherJob("讲师");
		double salaryOfJob1 = teacherDao.showSalaryOfTeacherJob("教授");
		double salaryOfJob2 = teacherDao.showSalaryOfTeacherJob("副教授");
		double salaryOfJob3 = teacherDao.showSalaryOfTeacherJob("讲师");
		
		result.setResult(1);
		result.setNumberOfJob1(numberOfJob1);
		result.setNumberOfJob2(numberOfJob2);
		result.setNumberOfJob3(numberOfJob3);
		result.setSalaryOfJob1(salaryOfJob1);
		result.setSalaryOfJob2(salaryOfJob2);
		result.setSalaryOfJob3(salaryOfJob3);

		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Teacher getModel() {
		return teacher;
	}

}
