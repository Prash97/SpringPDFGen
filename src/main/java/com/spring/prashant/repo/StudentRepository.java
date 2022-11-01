package com.spring.prashant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.prashant.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	public List<Student> findByName(String name);
	
	public List<Student> findByStandard(String standard); 
	
	public List<Student> findByDiv(String div);
}
