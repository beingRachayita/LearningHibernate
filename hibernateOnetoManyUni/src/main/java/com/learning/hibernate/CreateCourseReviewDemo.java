package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;

public class CreateCourseReviewDemo {
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
	
			//start transaction
			session.beginTransaction();
			//get Instructor from DB
			int id=1;
			Instructor ins = session.get(Instructor.class, id);
			//Create Courses
			Course course = new Course("Java Complete");
			Course co = new Course("DS and Algo");
			ins.add(co);
			ins.add(course);
			//Add some reviews
			course.add(new Review("Awesome Course!"));
			course.add(new Review("The Instructor did a great job explaining basics"));
			course.add(new Review("Dumb Course! Waste of Money"));
			
			co.add(new Review("Amazing Course, helped a lot"));
			co.add(new Review("Awesome Course!!"));
			//Save Courses, which will automatically save reviews due to cascade all
			
			session.save(course);
			
			session.save(co);
			
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
