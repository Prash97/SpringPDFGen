package com.spring.prashant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.prashant.entity.Student;
import com.spring.prashant.repo.StudentRepository;
import com.velocity.prashant.exception.ResourceNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	public final StudentRepository repository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Student getStuById(Integer Id) {
		Student student = repository.findById(Id).orElse(null);
		return student;
	}

	@Override
	public List<Student> getByName(String Name) {
		return repository.findByName(Name);
	}

	@Override
	public List<Student> getByStandard(String Standard) {
		return repository.findByStandard(Standard);
	}

	@Override
	public List<Student> getByDiv(String Div) {
		return repository.findByDiv(Div);
	}

	@Override
	public List<Student> getAll() {
		Iterable<Student> stuIterable = repository.findAll();
		List<Student> stuList = new ArrayList<>();
		stuIterable.forEach(x->stuList.add(x));
		return stuList;
	}

	@Override
	public void save(Student stu) {
		repository.save(stu);
	}

	@Override
	public void updateById(Integer Id, Student stu) {
		Student updateStudent = repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Student Id is not exist: "+ Id));
		updateStudent.setName(stu.getName());
		updateStudent.setStandard(stu.getStandard());
		updateStudent.setDiv(stu.getDiv());
		
		repository.save(updateStudent);
	}

	@Override
	public void deleteById(Integer Id) {
		repository.deleteById(Id);
	}
	

	
}
