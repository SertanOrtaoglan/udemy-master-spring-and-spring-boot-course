package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	/*('Spring JDBC' için)
	@Autowired
	private CourseJdbcRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		//CREATE
		repository.insert(new Course(1, "Learn AWS Now!", "in28minutes"));
		repository.insert(new Course(2, "Learn Azure Now!", "in28minutes"));
		repository.insert(new Course(3, "Learn DevOps Now!", "in28minutes"));
		
		//DELETE
		repository.deleteById(1);
		
		//SELECT
		System.out.println(repository.findById(2));
		System.out.println(repository.findById(3));
	}
	*/
	
	
	/*('JPA' için)
	@Autowired
	private CourseJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		//CREATE
		repository.insert(new Course(1, "Learn AWS Jpa!", "in28minutes"));
		repository.insert(new Course(2, "Learn Azure Jpa!", "in28minutes"));
		repository.insert(new Course(3, "Learn DevOps Jpa!", "in28minutes"));
		
		//DELETE
		repository.deleteById(1);
		
		//SELECT
		System.out.println(repository.findById(2));
		System.out.println(repository.findById(3));
	}
	*/
	
	
	//('Spring Data JPA' için)
	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		//CREATE
		repository.save(new Course(1, "Learn AWS Jpa!", "in28minutes"));
		repository.save(new Course(2, "Learn Azure Jpa!", "in28minutes"));
		repository.save(new Course(3, "Learn DevOps Jpa!", "in28minutes"));
		
		//DELETE
		repository.deleteById(1l);
		
		//SELECT
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
		//Son ders
		System.out.println(repository.findAll());  //H2'de bulunan tüm kursları yazdırmak için 'findAll()' methodunu kullanırız.
		System.out.println(repository.count());    //H2'de toplam kaç tane entity olduğunun sayısını verir.(2) 
		
		System.out.println(repository.findByAuthor("in28minutes"));
		System.out.println(repository.findByAuthor(""));  //Boş liste [] döndürür.
		
		System.out.println(repository.findByName("Learn AWS Jpa!"));   //Boş liste [] döndürür. Çünkü bu değere(Learn AWS Jpa!) sahip id'yi(1) yukarıda 'repository.deleteById(1l);' operasyonu ile sildik. 
		System.out.println(repository.findByName("Learn DevOps Jpa!"));
		
	}
	
	
}


