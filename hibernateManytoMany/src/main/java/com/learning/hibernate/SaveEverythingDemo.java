package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;
import com.learning.entity.Review;
import com.learning.entity.Student;

public class SaveEverythingDemo {
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
			
			//Create Instructor Objects
			Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			InstructorDetail instDetail = new InstructorDetail("http://www.youtube.com/madhu", "Guitar");
			
			Instructor instructor2 = new Instructor("Chad", "darby", "chad@luv2code.com");
			InstructorDetail instDetail2 = new InstructorDetail("http://www.youtube.com/chad", "Gaming");
			//Associate Objects
			instructor.setInstructorDetail(instDetail);
			instructor2.setInstructorDetail(instDetail2);
			
			//Save Instructors
			session.save(instructor);
			session.save(instructor2);
			
			//Create Courses
			Course course = new Course("Java Complete");
			Course co = new Course("DS and Algo");
			instructor.add(co);
			instructor.add(course);
			
			Course course1 = new Course("JavaScript Complete");
			Course course2 = new Course("Guitar Classes");
			instructor2.add(course1);
			instructor2.add(course2);
			//Add some reviews
			course.add(new Review("Awesome Course!"));
			course.add(new Review("The Instructor did a great job explaining basics"));
			course.add(new Review("Dumb Course! Waste of Money"));
			
			co.add(new Review("Amazing Course, helped a lot"));
			co.add(new Review("Awesome Course!!"));
			
			course1.add(new Review("Awesome Course!"));
			course1.add(new Review("The Instructor did a great job explaining basics"));
			course1.add(new Review("Dumb Course! Waste of Money"));
			
			course2.add(new Review("Awesome Course! Made guitar so easy to learn"));
			course2.add(new Review("The Instructor did a great job explaining basics"));
			
			//Save Courses, which will automatically save reviews due to cascade all
			session.save(course);
			session.save(co);
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
