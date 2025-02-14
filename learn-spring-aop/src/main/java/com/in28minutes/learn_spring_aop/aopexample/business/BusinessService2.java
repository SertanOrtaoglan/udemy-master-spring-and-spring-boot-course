package com.in28minutes.learn_spring_aop.aopexample.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.in28minutes.learn_spring_aop.aopexample.data.DataService2;


@Service
public class BusinessService2 {
	
	private DataService2 dataService2;
	
	public BusinessService2(DataService2 dataservice2) {
		this.dataService2 = dataservice2;
	}

	
	public int calculateMin() {
		int[] data = dataService2.retrieveData();
		
		//throw new RuntimeException("Something Went Wrong");
		return Arrays.stream(data).min().orElse(0);
	}

}
