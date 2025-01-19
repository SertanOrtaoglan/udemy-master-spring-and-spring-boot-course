package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional   //JPA ile çalışıyorsak 'EntityManager'ın çalışabilmesi için 'Transactional' annotation'ı eklememiz gerekir.
public class CourseJpaRepository {
	
	@PersistenceContext    //'Autowired' yerine 'EntityManager' tarafından yönetilen daha spesifik bir annotation olan 'PersistenceContext'i kullanırız.('Autowired' ile benzerdir.)
	private EntityManager entityManager;
	
	
	public void insert(Course course) {
		entityManager.merge(course);
		
	}
	
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}
	
	public void deleteById(long id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}
	

}


