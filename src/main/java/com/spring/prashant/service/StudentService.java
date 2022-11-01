package com.spring.prashant.service;

import java.util.List;

import com.spring.prashant.entity.Student;

public interface StudentService {

	// Get
	public Student getStuById(Integer Id);
	public List<Student> getByName(String Name);
	public List<Student> getByStandard(String Standard);
	public List<Student> getByDiv(String Div);

	//GetAll
	public List<Student> getAll();

	//save
	public void save(Student stu);

	//updateById
	public void updateById(Integer Id, Student stu);
	
	//delete
	public void deleteById(Integer Id);
}
