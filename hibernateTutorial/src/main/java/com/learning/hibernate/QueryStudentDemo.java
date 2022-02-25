package com.learning.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			//Read all the students from Student Table
			List<Student> studs = session.createQuery("from Student").getResultList();
			
			display(studs);
			
			//Read only Students with a particular last name:
			List<Student> students = session.createQuery("from Student s where s.lastName='Duck'").getResultList();
			
			display(students);		
			
			List<Student> stud1 = session.createQuery("from Student s where s.lastName='Duck' OR s.firstName='John'").getResultList();
			display(stud1);
			
			List<Student> stud2 = session.createQuery("from Student s where s.email LIKE '%abc.com'").getResultList();
			display(stud2);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
			
		}
		
	}
	
	public static void display(List<Student> studs) {
		for(Student s: studs) {
			System.out.println(s.toString());
		}
	}
}
