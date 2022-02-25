package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;

public class CreateCourseDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
	
			//start transaction
			session.beginTransaction();
			
			//get Instructor from DB
			int id=1;
			Instructor ins = session.get(Instructor.class, id);
			
			//Create courses
			Course temp1 = new Course("DataStructure & Algo");
			Course temp2 = new Course("The Piano");
			
			//add courses to instructor
			ins.add(temp1);
			ins.add(temp2);
			//save courses
			session.save(temp1);
			session.save(temp2);
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
