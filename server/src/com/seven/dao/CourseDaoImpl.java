package com.seven.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.seven.entity.Course;
import com.seven.entity.Learning;
import com.seven.entity.Teacher;
import com.seven.util.HibernateSessionFactory;

public class CourseDaoImpl implements CourseDao {
	private Transaction transaction;
	
	@Override
	public Course getCourse(int courseId) {
		Session session = HibernateSessionFactory.getSession();
		Course course = (Course)session.load(Course.class, courseId);
		return course;
	}

	@Override
	public boolean getCourseByNo(String courseNo) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Course as a where a.courseNo='" + courseNo + "'";
		Query query = (Query) session.createQuery(hql);
		Course course = (Course) query.uniqueResult();
		if(course != null){
			HibernateSessionFactory.closeSession();
			return true;
		}				
		HibernateSessionFactory.closeSession();
		return false;
	}

	@Override
	public int getCourseIdByNo(String courseNo) {
		int courseId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Course as a where a.courseNo='" + courseNo + "'";
		Query query = session.createQuery(hql);
		Course course = (Course) query.uniqueResult();
		if(course != null){
			courseId = course.getCourseId();
			HibernateSessionFactory.closeSession();
			return courseId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}

	@Override
	public void saveCourse(Course course) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			session.save(course);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			System.out.println("saveCourse Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	
	}

	@Override
	public void deleteCourse(int courseId) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Course course = (Course)session.get(Course.class,courseId);
			if (course != null) {
				session.delete(course);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("deleteCourse Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	

	}


	@Override
	public void updateCourseInfo(int courseId, Teacher teacher,
			String courseNo, String courseName, int courseScore) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Course course = (Course)session.get(Course.class,courseId);
			if(course != null){
				course.setCourseName(courseName);
				course.setTeacher(teacher);
				course.setCourseScore(courseScore);
				session.update(course);
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
	public int getCourseIdByLearningId(int learningId) {
		int courseId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Learning as a where a.learningId='" + learningId + "'";
		Query query = session.createQuery(hql);
		Learning ls = (Learning) query.uniqueResult();
		if(ls != null){
			courseId = ls.getCourse().getCourseId();
			HibernateSessionFactory.closeSession();
			return courseId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}
}
