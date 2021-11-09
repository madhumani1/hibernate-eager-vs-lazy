/**
 * 
 */
package com.main.java.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 15197
 *	1 Instructor for 1 or more courses
 */
@Entity
@Table(name="course")
public class Course {
	// define our fields
	// annotate fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	// course has many to 1 relationship with instructor
	// do NOT apply cascading DELETE
	// cascade type - if you delete a course, do NOT delete instructor
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	// define our Instructor
	/**
	 * 
	 */
	public Course() {
		//
	}
	
	/**
	 * @param title
	 */
	public Course(String title) {
		this.title = title;
	}

	
	// define getters and setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the instructor
	 */
	public Instructor getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	// toString()
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}	
}
