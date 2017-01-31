package com.account.management.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.account.management.domain.Course;
import com.account.management.domain.Student;
import com.account.management.exceptions.InvalidCardException;
import com.account.management.exceptions.UnauthorizedPaymentException;

public class StudentCheckoutService {
	@Autowired
	@Qualifier("paymentForPayPal")
	private PaymentService payService;
	@Autowired
	@Qualifier("tuitoncalcNationalState")
	private TuitionCalculatorService tuitionCalcService;

	public void setPaymentService(PaymentService payService) {
		this.payService = payService;
	}
	public void setTuitionCalculatorService(TuitionCalculatorService tuitionCalcService) {
		this.tuitionCalcService = tuitionCalcService;
	}
	public StudentCheckoutService() {
	}
	public void checkout(Student student, ArrayList<Course> courseList, String creditCard) throws InvalidCardException, UnauthorizedPaymentException {
		
		System.out.println("Student details are:");
		System.out.println("Name: "+ student.getStudentName());
		System.out.println("Id: "+ student.getStudentId());
		System.out.println("Education Type: "+ student.getEducation());
		System.out.println("International Student: "+ student.getStudentTypeIntl());
		System.out.println();


		double feesDue=tuitionCalcService.computeTuition(student, courseList);
		payService.makePayment(student, creditCard,feesDue);
		
	

		// Adding the courseList to student
		student.setCourseList(courseList);

		System.out.println("The student count in these courses are");
		for (int i = 0; i < courseList.size(); i++) {
			int count = courseList.get(i).getStudentCount();
			// Updating enrollment number for the courses
			courseList.get(i).setStudentCount(count + 1);
			System.out.print(courseList.get(i).getCourseName() + ":" + courseList.get(i).getStudentCount());
			System.out.println();
		}

	}

}
