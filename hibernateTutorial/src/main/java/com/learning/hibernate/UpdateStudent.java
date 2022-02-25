package com.learning.hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Student;

public class UpdateStudent {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			int studentId=6;
			
			session.beginTransaction();
			//Any object attach to a session is persistent obj
			Student stud = session.get(Student.class, studentId);
			//the changes to the student obj will automatically get updated into DB on commit, as it is persistent obj
			stud.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
