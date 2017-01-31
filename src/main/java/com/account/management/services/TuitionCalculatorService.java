package com.account.management.services;

import java.util.ArrayList;

import com.account.management.domain.*;

public interface TuitionCalculatorService {
	public double computeTuition(Student student, ArrayList<Course> courseList);
}
