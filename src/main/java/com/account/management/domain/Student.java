package com.account.management.domain;

import java.util.ArrayList;

public class Student {

	private String studentName;
	private String studentId;
	private boolean studentTypeIntl;
	private String education;
	private ArrayList<Course> courseList;

	public Student(String studentName, String studentId, boolean studentTypeIntl, String education, ArrayList<Course> courseList) {

		this.studentName = studentName;
		this.studentId = studentId;
		this.studentTypeIntl = studentTypeIntl;
		this.education = education;
		this.courseList = courseList;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public boolean getStudentTypeIntl() {
		return studentTypeIntl;
	}

	public void setStudentTypeIntl(boolean studentTypeIntl) {
		this.studentTypeIntl = studentTypeIntl;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

}
