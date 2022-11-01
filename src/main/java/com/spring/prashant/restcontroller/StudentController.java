package com.spring.prashant.restcontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.spring.prashant.entity.Student;
import com.spring.prashant.pdfexpo.PdfExporter;
import com.spring.prashant.service.StudentService;
import com.velocity.prashant.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/id/{Id}")
	public ResponseEntity<Student> getById(@PathVariable Integer Id){
		Student student = service.getStuById(Id);
		if (student!=null) {
			return ResponseEntity.ok(student);
		}else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get")
	public List<Student> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException{
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTimeString = dateFormat.format(new Date());
		
		String headerKeyString = "Content-Disposition";
		String headerValue ="attachment; filename=students_"+currentDateTimeString+".pdf";
		response.setHeader(headerKeyString, headerValue);
		
		List<Student> liststudent = service.getAll();
		
		PdfExporter exporter = new PdfExporter(liststudent);
		exporter.export(response);
	
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestBody Student stu) {
		if (stu.getName()!=null) {
			service.save(stu);
			return "Record is Added..";
		} else {
			return "Record is null..";
		}
	}
	
	@GetMapping("/byname")
	public List<Student> getByName(@RequestParam String Name){
		return service.getByName(Name);
	}
	
	@GetMapping("/bystd")
	public List<Student> getByStandard(@RequestParam String std){
		return service.getByStandard(std);
	}
	 
	@GetMapping("/div/{div}")
	public List<Student> getByDiv(@PathVariable String div){
		return service.getByDiv(div);
	}
	
	@PutMapping("/update/{Id}")
	public ResponseEntity<String> updateStudent(@PathVariable Integer Id,@RequestBody Student stu){
		try {
			service.updateById(Id, stu);
			return ResponseEntity.ok("Record Updated..");
			
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{Id}")
	public String deleteStudentById(@PathVariable Integer Id) {
		service.deleteById(Id);
		return "Record is Deleted";
	}
}
