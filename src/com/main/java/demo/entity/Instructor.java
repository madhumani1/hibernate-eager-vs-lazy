/**
 * 
 */
package com.main.java.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author 15197
 * 1-1 mapping - POJO to map Object with Relational Model. That is Class or Entity to database table
 * courses as fetchtype eager or lazy
 */
@Entity
@Table(name="instructor")
public class Instructor {
	/**
	 * annotate the class as in entity and map to db table
	 * define the fields 
	 * annotate the fields with db column names
	 * setup mapping to InstructorDetail entity 
	 * create constructors
	 * generate getters and setters
	 * generate the toString() method
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	// one to one mapping with cascade type all 
	// so this will apply to any operations for persisting, deleting or anything regarding the object.
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="instructor",cascade= {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Course> courses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the instructorDetail
	 */
	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	/**
	 * @param instructorDetail the instructorDetail to set
	 */
	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	/**
	 * 
	 */
	public Instructor() {
		//
	}

	/**
	 * @param id
	 * @param youtubeChannel
	 * @param hobby
	 */
	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	// add convenience methods for bidirectional relationship
	
	public void add(Course tempCourse)	{
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
		tempCourse.setInstructor(this);
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
