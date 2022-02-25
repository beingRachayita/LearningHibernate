package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Instructor;
import com.learning.entity.InstructorDetail;

public class DeleteBiDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Instructor.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			//get Inst detail
			int id = 3;
			InstructorDetail temp = session.get(InstructorDetail.class, id);
			
			//print Inst detail
			System.out.println(temp);
			
			//print associated Instructor
			System.out.println("Associated Instructor: "+ temp.getInstructor().toString());
			//remove the reference from Instructor for the delete to be successful
			Instructor ins = temp.getInstructor();
			ins.setInstructorDetail(null);
			//Delete
			session.delete(temp);
			
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
