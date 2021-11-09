/**
 * 
 */
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
public class FetchJoinDemo {

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
			//Instructor tempInstructor = session.get(Instructor.class, theId);
			//hibernate query with HQL
			Query query = session.createQuery("select i from Instructor i " + 
						"JOIN FETCH i.courses " + 
						"WHERE i.id= :theInstructorId");
			
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			List<Instructor> tempInstructor = query.list();
			
			System.out.println("Madhukar: Instructor: " + tempInstructor);
			
			// get course for the instructor
			// System.out.println("Madhukar: Courses: " + tempInstructor.getCourses());
			
			// commit the transaction
			session.getTransaction().commit();
			if(session.isOpen())	{
				session.close();
			}
			
			System.out.println("The session is now closed");
			
			// option 2: loading the data using HQL
			System.out.println("Madhukar: Courses: " + tempInstructor.get(0).getCourses());
			
			System.out.println("Madhukar: Done!");
		}	finally	{
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
