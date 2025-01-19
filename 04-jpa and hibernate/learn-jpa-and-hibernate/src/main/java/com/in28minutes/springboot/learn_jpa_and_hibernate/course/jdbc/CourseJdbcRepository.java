package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;

@Repository
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	/*(ilk ders)
	private static String INSERT_QUERY = 
			""" 
			insert into course (id, name, author)
			values(1, 'Learn AWS', 'in28minutes')
			
			""";
	
	public void insert() {
		springJdbcTemplate.update(INSERT_QUERY);
		
	}
	*/

	//(ikinci ders)
	//CREATE 
	private static String INSERT_QUERY = 
			""" 
			insert into course (id, name, author)
			values(?, ?, ?)
			
			""";
	
	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
	}

	//DELETE
	private static String DELETE_QUERY = 
			""" 
			delete from course
			where id = ?
			
			""";
	
	public void deleteById(long id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}
	
	//Üçüncü ders
	//SELECT(READ)
	private static String SELECT_QUERY = 
			""" 
			select * from course
			where id = ?
			
			""";
	
	public Course findById(long id) {
		//ResulSet -> Bean'e mapping yapacağız ve bunun için 'Row Mapper'ı kullanacağız.
		//id (parameter)
		//Özetlersek bu işlem sonucunda bize H2'den bir ResultSet geliyor(mesela parametre olarak '1' nolu id'yi verdik diyelim; '1' numaralı id, 'name'i ve 'author'u) ve biz gelen ResultSet'i, 'Course' bean'ine dönüştürmek istiyoruz. Yani mapping yapacağız. Dolayısıyla 'queryForObject()' operasyonu içerisinde mapping yapabilmek için 'BeanPropertyRowMapper'ı kullanırız. 
		
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
	}
	
	
	
}

