package com.learning.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "course_student", 
				joinColumns = @JoinColumn(name = "course_id"), 
				inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;

	// Add a convenience method
		public void addStudent(Student st) {
			if (students == null) {
				students = new ArrayList<Student>();
			}
			students.add(st);
		}
		
	//Constructors, Setters Getters
	
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Student> getStudent() {
		return students;
	}

	public void setStudent(List<Student> student) {
		this.students = student;
	}

	@Override
	public String toString() {
		return "Course [Id=" + Id + ", title=" + title + ", instructor=" + instructor + "]";
	}

	

	// add a convenience method
	public void add(Review temp) {
		if (reviews == null) {
			reviews = new ArrayList<Review>();
		}

		reviews.add(temp);
	}

}
