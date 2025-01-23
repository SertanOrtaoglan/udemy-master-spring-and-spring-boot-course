package com.in28minutes.learn_spring_aop.aopexample.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.in28minutes.learn_spring_aop.data.DataService1;

@Service
public class BusinessService1 {
	
	private DataService1 dataservice1;
	
	public BusinessService1(DataService1 dataservice1) {
		this.dataservice1 = dataservice1;
	}

	
	public int calculateMax() {
		int[] data = dataservice1.retrieveData();
		return Arrays.stream(data).max().orElse(0);
	}

}
