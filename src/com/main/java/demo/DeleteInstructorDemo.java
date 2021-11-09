/**
 * 
 */
package com.main.java.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;

/**
 * @author 15197
 *
 */
public class DeleteInstructorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("emp.hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try	{
			int theId = 1;
			
			// now get a new session and start transaction
			session.beginTransaction();
			
			// retrieve Instructor based on the id: primary key
			System.out.println("\nGetting Instructor with id: " + theId);
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("Found instructor: " + tempInstructor);
			
			// delete the instructor
			if (tempInstructor != null) {
				
				System.out.println("Deleting instructor: " + tempInstructor);
				
				// Note: will ALSO delete associated "details" object
				// because of CascadeType.ALL
				//
				session.delete(tempInstructor);				
			}
			
			// delete the instructors
			//System.out.println("Deleting instructor id=1");
			
			//session.createQuery("delete from Instructor where id=1").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}	finally	{
			factory.close();
		}

	}

}
