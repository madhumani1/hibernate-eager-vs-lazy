/**
 * 
 */
package com.main.java.demo;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.query.dsl.impl.ConnectedMoreLikeThisQueryBuilder.MoreLikeThisToEntityContentAndTerminationImpl;

import com.main.java.demo.entity.Course;
import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;

/**
 * @author 15197
 *
 */
public class EagerLazyDemo {

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
			// if fetchtype is eager, then at this point everything is loaded: Instructor, InstructorDetails and Courses
			// beyond this line, there is no need to hit database anymore because everything is loaded already 
			
			System.out.println("Madhukar: Instructor: "+tempInstructor);
			
			// get course for the instructor
			//System.out.println("Madhukar: Courses: " + tempInstructor.getCourses());
			
			// commit the transaction
			session.getTransaction().commit();
			
			if(session.isOpen())	{
				session.close();
			}
			// close session - THIS WILL BREAK CODE AND THROW EXCEPTION. DOING IT TO TEST LAZY FETCH THAT COURSE
			// DATA IS FETCHED ON DEMAND. NOTE: Courses are lazy loaded from Instructor.
			// get course for the instructor. IT WILL THROW  org.hibernate.LazyInitializationException
			System.out.println("Madhukar: Courses: " + tempInstructor.getCourses());
			System.out.println("Madhukar: Done!");
		}	finally	{
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
