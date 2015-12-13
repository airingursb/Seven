package com.seven.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.seven.entity.Learning;
import com.seven.util.HibernateSessionFactory;

public class LearningDaoImpl implements LearningDao {
	
	private Transaction transaction;
	
	@Override
	public void addAchieve(int learningId, int courseAchieve) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Learning learning = (Learning)session.get(Learning.class,learningId);
			if(learning != null){
				learning.setCourseAchieve(courseAchieve);
				System.out.println(courseAchieve);
				session.update(learning);
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
	public Learning getLearning(int learningId) {
		Session session = HibernateSessionFactory.getSession();
		Learning learning = (Learning)session.load(Learning.class, learningId);
		return learning;
	}

	@Override
	public int getLearningId(int courseId, int studentId) {
		int learningId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Learning as a where a.course.courseId='" + courseId 
				+ "' and a.student.studentId='" + studentId + "'";
		Query query = session.createQuery(hql);
		Learning learning = (Learning) query.uniqueResult();
		if(learning != null){
			learningId = learning.getLearningId();
			HibernateSessionFactory.closeSession();
			return learningId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}

	@Override
	public Set<Learning> getLearningByCourseId(int courseId) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Learning as a where a.course.courseId='" + courseId + "'";
		System.out.println(hql);
		Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list = query.list(); 
		System.out.println("size:" + list.size());
        Set<Learning> learnings = new HashSet<Learning>();
        for(int i = 0; i < list.size(); i++){  
        	Learning learning = (Learning) list.get(i);  
        	learnings.add(learning);  
        	System.out.println(learning.getLearningId());
        }   
        HibernateSessionFactory.closeSession();
		return learnings;
	}

	@Override
	public Set<Learning> getLearningByStudentId(int studentId) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Learning as a where a.student.studentId='" + studentId + "'";
		System.out.println(hql);
		Query query = (Query) session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list = query.list(); 
		System.out.println("size:" + list.size());
        Set<Learning> learnings = new HashSet<Learning>();
        for(int i = 0; i < list.size(); i++){  
        	Learning learning = (Learning) list.get(i);  
        	learnings.add(learning);  
        	System.out.println(learning.getLearningId());
        }   
        HibernateSessionFactory.closeSession();
		return learnings;
	}

}
