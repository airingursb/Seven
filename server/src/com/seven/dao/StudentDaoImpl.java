package com.seven.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.seven.entity.Course;
import com.seven.entity.Learning;
import com.seven.entity.Student;
import com.seven.util.HibernateSessionFactory;

public class StudentDaoImpl implements StudentDao {
	private Transaction transaction;

	@Override
	public Student getStudent(int studentId) {
		Session session = HibernateSessionFactory.getSession();
		Student student = (Student)session.load(Student.class, studentId);
		return student;
	}

	@Override
	public boolean getStudentByNo(String studentNo) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Student as a where a.studentNo='" + studentNo + "'";
		Query query = (Query) session.createQuery(hql);
		Student student = (Student) query.uniqueResult();
		if(student != null){
			HibernateSessionFactory.closeSession();
			return true;
		}				
		HibernateSessionFactory.closeSession();
		return false;
	}

	@Override
	public int getStudentIdByNo(String studentNo) {
		int studentId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Student as a where a.studentNo='" + studentNo + "'";
		Query query = session.createQuery(hql);
		Student student = (Student) query.uniqueResult();
		if(student != null){
			studentId = student.getStudentId();
			HibernateSessionFactory.closeSession();
			return studentId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}

	@Override
	public void saveStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			System.out.println("saveStudent Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	
	}

	@Override
	public void deleteStudent(int studentId) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if (student != null) {
				session.delete(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("deleteStudent Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}	

	}
	
	@Override
	public void updateStudentInfo(int studentId, String studentPassword,
			String studentName, String studentSex, int studentAge) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if(student != null){
				student.setStudentPassword(studentPassword);
				student.setStudentName(studentName);
				student.setStudentSex(studentSex);
				student.setStudentAge(studentAge);
				session.update(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("updateStudentPassword Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}

	@Override
	public void updateStudentPassword(int studentId, String studentPassword) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if(student != null){
				student.setStudentPassword(studentPassword);
				session.update(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("updateStudentPassword Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void updateStudentName(int studentId, String studentName) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if(student != null){
				student.setStudentName(studentName);
				session.update(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("updateStudentName Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void updateStudentSex(int studentId, String studentSex) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if(student != null){
				student.setStudentSex(studentSex);
				session.update(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("updateStudentSex Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void updateStudentAge(int studentId, int studentAge) {
		Session session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			Student student = (Student)session.get(Student.class,studentId);
			if(student != null){
				student.setStudentAge(studentAge);
				session.update(student);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			System.out.println("updateStudentAge Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean studentLogin(Student student) {
		if(student.getStudentNo()!=null && student.getStudentPassword()!=null){
			Session session = HibernateSessionFactory.getSession();
			try{
				String hql = "from Student as a where a.studentNo='"+student.getStudentNo()+"' and a.studentPassword='"+student.getStudentPassword()+"'";
				System.out.println("hql:"+hql);
				Query query = (Query) session.createQuery(hql);
				Student s = (Student) query.uniqueResult();
				if(s!=null){
					HibernateSessionFactory.closeSession();
					return true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
		return false;
	}

	@Override
	public void selectCourse(Student student, Course course) {
		Session session = HibernateSessionFactory.getSession();
		Learning l = new Learning();
		try{
			transaction = session.beginTransaction();
			l.setStudent(student);
			l.setCourse(course);
			l.setCourseAchieve(0);
			session.save(l);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			System.out.println("selectCouser Failed!");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}		
	}

	public Set<Learning> showMyCourse(int studentId) {
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

	@Override
	public int getStudentIdByLearningId(int learningId) {
		int studentId = 0;
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Learning as a where a.learningId='" + learningId + "'";
		Query query = session.createQuery(hql);
		Learning ls = (Learning) query.uniqueResult();
		if(ls != null){
			studentId = ls.getStudent().getStudentId();
			HibernateSessionFactory.closeSession();
			return studentId;
		}
		HibernateSessionFactory.closeSession();
		return -1;
	}

	@Override
	public Set<Student> getAllStudents() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Student";
        Query query = session.createQuery(hql);
        @SuppressWarnings("rawtypes")
		List list = query.list();
		Set<Student> allStudents = new HashSet<Student>();
        for(int i = 0; i < list.size(); i++){  
        	Student student = (Student) list.get(i);  
        	allStudents.add(student);     
        }  
		return allStudents;
	}


}
