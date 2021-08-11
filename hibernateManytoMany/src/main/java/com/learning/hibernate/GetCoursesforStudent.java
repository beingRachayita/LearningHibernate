package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;
import com.learning.entity.Student;

public class GetCoursesforStudent {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
	
			//start transaction
			session.beginTransaction();
			
			//Get the student
			int id=1;
			Student stud = session.get(Student.class, id);
			
			System.out.println("Loading Student: "+ stud);
			//get courses
			System.out.println("Getting Courses!! \n\n");
			System.out.println(stud.getCourses());
			
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
