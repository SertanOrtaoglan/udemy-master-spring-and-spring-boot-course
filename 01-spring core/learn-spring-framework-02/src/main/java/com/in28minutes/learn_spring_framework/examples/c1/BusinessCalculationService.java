package com.in28minutes.learn_spring_framework.examples.c1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component
@Service   //'Component' annotation'ı yerine 'Service' annotation'ını kullanabiliriz. Çünkü class içerisinde 'business logic(iş mantıkları)'ler tanımlanmıştır. 
public class BusinessCalculationService {
	private DataService dataService;
	
	@Autowired
	public BusinessCalculationService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public int findMax() {
		return Arrays.stream(dataService.retrieveData()).max().orElse(0);
	}
	

}
