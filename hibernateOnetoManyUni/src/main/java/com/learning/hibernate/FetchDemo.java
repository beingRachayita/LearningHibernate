package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.entity.Course;
import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;

public class FetchDemo {
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
			
			int id=1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
													+"JOIN FETCH i.courses "
													+"where i.id=:theInstructorId", Instructor.class);
			query.setParameter("theInstructorId", id);
			
			Instructor ins = query.getSingleResult();
			
			System.out.println("Hey!! Instructor: "+ ins);
			
			//commit
			session.getTransaction().commit();
			
			session.close();
			
			//Display the associated Courses
			System.out.println("Courses :"+ ins.getCourses());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
			
		}
		
	}
}
