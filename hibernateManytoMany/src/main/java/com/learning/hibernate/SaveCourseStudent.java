package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;
import com.learning.entity.Student;

public class SaveCourseStudent {
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
			//Create Courses
			Course course = new Course("Java Complete");
			Course course1 = new Course("JavaScript Complete");
			Course course2 = new Course("Guitar Classes");
			
			System.out.println("Saving Courses");
			//Save Courses
			session.save(course);
			session.save(course1);
			session.save(course2);
		
			//Create Students
			Student stud1 = new Student("Mary", "Poppins","poppins@gmail.com");
			Student stud2 = new Student("Robin", "Hood","hood@gmail.com");
			
			//create association bw student and course
			course.addStudent(stud1);
			course.addStudent(stud2);
			course2.addStudent(stud1);
			course1.addStudent(stud1);
			
			System.out.println("Saving Students");
			//save Students
			session.save(stud1);
			session.save(stud2);
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
