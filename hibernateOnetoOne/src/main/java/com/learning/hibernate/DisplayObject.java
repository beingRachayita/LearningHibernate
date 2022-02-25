package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;

public class DisplayObject {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
			//Get Instructor Details
			int id=5;
			Instructor ins = session.get(Instructor.class, id);
			
			//Display Instructor
			System.out.println("Instructor: "+ ins);				
			System.out.println("Instructor Details: "+ ins.getInstructorDetail());
			
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
