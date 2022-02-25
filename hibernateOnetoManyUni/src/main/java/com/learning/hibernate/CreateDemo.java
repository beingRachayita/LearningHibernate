package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;

public class CreateDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			//Create Objects
			Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			InstructorDetail instDetail = new InstructorDetail("http://www.youtube.com/madhu", "Guitar");
			
			//Associate Objects
			instructor.setInstructorDetail(instDetail);
			
			//start transaction
			session.beginTransaction();
			
			//As cascade type is set, this will save Instructor details as well
			session.save(instructor);
			
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
