package com.account.management.services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.account.management.domain.Course;
import com.account.management.domain.Student;

@Component("tuitoncalcNationalState")
public class TuitionCalcNationalUni implements TuitionCalculatorService {

	
	public double computeTuition(Student student, ArrayList<Course> courseList) {
		//System.out.println("Payment details of student from National University");

		double totalFees = 0.0;
		double feesPerCourse = 0.0;
		System.out.println("Courses selected and breakage of fees is as below :");
		for (int i = 0; i < courseList.size(); i++) {

			int units = courseList.get(i).getUnits();
			if (student.getStudentTypeIntl() == true) {
				feesPerCourse = units * 500;
			} else {
				feesPerCourse = units * 230;
			}
			System.out.println(courseList.get(i).getCourseName()+": $" + feesPerCourse);
			totalFees += feesPerCourse;
		}
		
		System.out.println("Total fees to be paid is: $" +totalFees);
		 
		return totalFees;
		

	}

}
