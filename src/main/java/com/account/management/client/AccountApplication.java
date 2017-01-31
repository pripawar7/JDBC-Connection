package com.account.management.client;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.account.management.domain.Course;
import com.account.management.domain.Student;
import com.account.management.exceptions.InvalidCardException;
import com.account.management.exceptions.UnauthorizedPaymentException;
import com.account.management.services.StudentCheckoutService;

/*Author : Manjushree Shetty
 * Id : 17422
 * Course : CS548
 */
public class AccountApplication {
	public static void main(String[] args) throws InvalidCardException, UnauthorizedPaymentException {

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("app-context.xml");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"file:src/main/resources/META-INF/spring/app-context.xml");

		StudentCheckoutService studentCheckout1 = (StudentCheckoutService) context.getBean("studentCheckoutProcess");

		ArrayList<Student> studList=new ArrayList<Student>();

		Course courseA = null, courseB = null, courseC = null;
		ArrayList<Course> courseList = new ArrayList<Course>();

		studList = createStudentObjects(studList);
		createCourseObjects(courseA, courseB, courseC, courseList);

		/*
		 * Case 1 : Run checkout process for a student with valid credit card
		 * number
		 */

		String creditCard1 = "111222333444555";
		String creditCard2 = "666669999911111";

		
		studentCheckout1.checkout(studList.get(0), courseList, creditCard1);
		studentCheckout1.checkout(studList.get(1), courseList, creditCard2);
		
		/*
		 * Case 2 : Run checkout process for a student with invalid credit card number
		 */

		String creditCard3 = "123";
		//studentCheckout1.checkout(studList.get(0),courseList,creditCard3);
		
		/*
		 * Case 3 : Run checkout process for a student with invalid credit card number
		 */

		String creditCard4 = "555222333444555";
	//	studentCheckout1.checkout(studList.get(0),courseList,creditCard4);

	}

	
	public static ArrayList<Student> createStudentObjects(ArrayList<Student> studList) {
		
		
	Student	student1 = new Student("Tim", "101", true, "PG", null);
	Student	student2=new Student("Joy","102",true,"UG",null);

		studList.add(student1);
		studList.add(student2);
		
		return studList;

	}

	public static void createCourseObjects(Course courseA,Course courseB,Course courseC, ArrayList<Course> courseList) {
		
		courseA=new Course("CS550","Computer Science","PG",3,0);
		courseB = new Course("CS551","Computer Science","PG",3,0);
		courseC=new Course("CS552","Computer Science","PG",3,0);
		
		courseList.add(courseA);
		courseList.add(courseB);
		courseList.add(courseC);
		
		

	}

}