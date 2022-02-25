package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;

public class UniDeleteDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			int id = 4;
			//get Instructor
			Instructor temp = session.get(Instructor.class, id);
			
			//delete Instructor
			if(temp!=null) {
				//this will also delete details obj, becoz of cascade
				session.delete(temp);
			}
		
			//commit
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
			
		}
		
	}
}
