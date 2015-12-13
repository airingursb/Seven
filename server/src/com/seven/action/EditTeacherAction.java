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

public class EditTeacherAction extends ActionSupport implements ModelDriven<Teacher> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5483013777966615330L;
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
		
		int teacherId = teacherDao.getTeacherIdByNo(teacher.getTeacherNo());
		teacherDao.updateTeacherInfo(teacherId, teacher.getTeacherName(),
				teacher.getTeacherJob(), teacher.getTeacherSalary());
		
		result.setResult(1);
		System.out.println(gson.toJson(result));
		out.println(gson.toJson(result));
		return null;
	}
	
	@Override
	public Teacher getModel() {
		return teacher;
	}
}
