package com.seven.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.seven.entity.Teacher;
import com.seven.util.HibernateSessionFactory;

public class TeacherDaoImpl implements TeacherDao {
	private Transaction transaction;
	
	@Override
	public Teacher getTeacher(int teacherId) {
		Session session = HibernateSessionFactory.getSession();
		Teacher teacher = (Teacher)session.load(Teacher.class, teacherId);
		return teacher;
	}

	@Override
	public boolean getTeacherByNo(String teacherNo) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Teacher as a where a.teacherNo='" + teacherNo + "'";
		Query query = (Query) session.createQuery(hql);
		Teacher teacher = (Teacher) query.uniqueResult();
		if(teacher != null){
			HibernateSessionFactory.closeSession();
			return true;
		}				
		HibernateSessionFactory.closeSession();
		return false;
	}

	@Override
	public int getTeacherIdByNo(String teacherNo) {
		int teacherId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Teacher as a where a.teacherNo='" + teacherNo + "'";
		Query query = session.createQuery(hql);
		Teacher teacher = (Teacher) query.uniqueResult();
		if(teacher != null){
			teacherId = teacher.getTeacherId();
			HibernateSessionFactory.closeSession();
			return teacherId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			session.save(teacher);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			System.out.println("saveTeacher Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	
	}

	@Override
	public void deleteTeacher(int teacherId) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Teacher teacher = (Teacher)session.get(Teacher.class,teacherId);
			if (teacher != null) {
				session.delete(teacher);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("deleteTeacher Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	

	}

	@Override
	public void updateTeacherInfo(int teacherId, String teacherName,
			String teacherJob, Double teacherSalary) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Teacher teacher = (Teacher)session.get(Teacher.class,teacherId);
			if(teacher != null){
				teacher.setTeacherName(teacherName);
				teacher.setTeacherJob(teacherJob);
				teacher.setTeacherSalary(teacherSalary);
				session.update(teacher);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("update Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}

	@Override
	public int showNumberOfTeacherJob(String teacherJob) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Teacher as a where a.teacherJob='" + teacherJob + "'";
		Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list = query.list(); 
        Set<Teacher> teachers = new HashSet<Teacher>();
        for(int i = 0; i < list.size(); i++){  
        	Teacher teacher = (Teacher) list.get(i);  
        	teachers.add(teacher);     
        }   
		return teachers.size();
	}

	@Override
	public double showSalaryOfTeacherJob(String teacherJob) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Teacher as a where a.teacherJob='" + teacherJob + "'";
		Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list = query.list(); 
        Set<Teacher> teachers = new HashSet<Teacher>();
		double salary = 0;
        double avgOfSalary = 0;
        for(int i = 0; i < list.size(); i++){  
        	Teacher teacher = (Teacher) list.get(i);
        	salary = salary + teacher.getTeacherSalary();
        	teachers.add(teacher);  
        }   
    	avgOfSalary = salary / teachers.size();
		return avgOfSalary;	
	}


}
