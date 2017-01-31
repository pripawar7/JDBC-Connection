package com.account.management.domain;

public class Course {

	private String courseName;
	private String departmentName;
	private String courseType;
	private int units;
	private int studentCount;

	public Course(String courseName, String departmentName, String courseType, int units, int studentCount) {
		
		this.courseName = courseName;
		this.departmentName = departmentName;
		this.courseType = courseType;
		this.units = units;
		this.studentCount = studentCount;
	}



	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

}
