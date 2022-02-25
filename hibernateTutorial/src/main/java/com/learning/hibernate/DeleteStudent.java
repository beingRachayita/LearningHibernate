package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Student;

public class DeleteStudent {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			int id=6;
			/*
			 * session.beginTransaction();
			 * 
			 * Student stud = session.get(Student.class, id);
			 * 
			 * session.delete(stud);
			 * 
			 * session.getTransaction().commit();
			 */
			session.beginTransaction();
			
			session.createQuery("delete from Student s where s.id =7").executeUpdate();
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}

	}

}
