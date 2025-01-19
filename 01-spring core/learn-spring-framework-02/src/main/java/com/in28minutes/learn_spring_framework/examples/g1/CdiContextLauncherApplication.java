package com.in28minutes.learn_spring_framework.examples.g1;

import java.util.Arrays;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;

import jakarta.inject.Inject;
import jakarta.inject.Named;

//@Component
@Named    //'@Component' ile aynı şeydir.(Jakarta CDI'da 'Component' yerine 'Named' kullanılır.)
class BusinessService {
	private DataService dataService;

	//@Autowired
	@Inject  //'@Autowired' ile aynı şeydir.(Jakarta CDI'da 'Autowired' yerine 'Inject' kullanılır.)
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}
	
	public DataService getDataService() {
		//System.out.println("Setter Injection");
		return dataService;
	}
	
}

//@Component
@Named
class DataService {
	
}


@Configuration
@ComponentScan  //Direkt olarak bu pakette tarama yapacaktır.
public class CdiContextLauncherApplication {

	public static void main(String[] args) {

		try(var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class).getDataService());
		}
		
	}

}

