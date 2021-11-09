package com.main.java.demo;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.main.java.demo.entity.Course;
import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;

/**
 * @author 15197
 *
 */
public class GetCoursesLater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("emp.hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try	{
			// now use the session object to save/retrieve Java objects
			// start transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("Madhukar: Instructor: "+tempInstructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// close the session
			if(session.isOpen())	{
				session.close();
			}
			System.out.println("The session is now closed");
			
			// THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM
			// YOU NEED TO GET A NEW SESSION
			
			System.out.println("\n\nMadhukar: Opening a NEW session \n");
			session = factory.getCurrentSession();
			
			// start transaction
			session.beginTransaction();
			
			// Get Courses for given Instructor
			Query query = session.createQuery("select c from Course c " + 
						"WHERE c.instructor.id= :theInstructorId");
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			List<Course> tempCourses = query.list();
			
			System.out.println("Madhukar: tempCourses: " + tempCourses);
			
			// now assign to instructor object in memory
			tempInstructor.setCourses(tempCourses);
			
			System.out.println("Madhukar: Courses: " + tempInstructor.getCourses());
			
			// get course for the instructor
			System.out.println("Madhukar: Courses: " + tempInstructor.getCourses());
			
			// commit the transaction
			session.getTransaction().commit();
			if(session.isOpen())	{
				session.close();
			}
			
			System.out.println("The session is now closed");
			
			
			System.out.println("Madhukar: Done!");
		}	finally	{
			// close the session
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
