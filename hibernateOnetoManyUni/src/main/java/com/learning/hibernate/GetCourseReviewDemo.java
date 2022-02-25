package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;

public class GetCourseReviewDemo {
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
			int id1=10;
			int id2=11;
			//get Course
			Course co1= session.get(Course.class, id1);
			Course co2= session.get(Course.class, id2);
			//print course
			System.out.println("Course 1: "+ co1);
			System.out.println("Course 1 reviews "+ co1.getReviews());
			//print review
			System.out.println("Course 2: "+ co2);
			System.out.println("Course 2 reviews "+ co2.getReviews());
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
