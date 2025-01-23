package com.in28minutes.learn_spring_framework.examples.a1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//YourBusinessClass (Dependency1 and Dependency2)
@Component
class YourBusinessClass{
	
	/*Field Injection
	@Autowired
	Dependency1 dependency1;
	@Autowired
	Dependency2 dependency2;
	
	public String toString() {
		return "Using " + dependency1 + " and " + dependency2;
	}
	*/
	
	/*Setter Injectıon
	Dependency1 dependency1;
	Dependency2 dependency2;
	
	@Autowired
	public void setDependency1(Dependency1 dependency1) {
		System.out.println("Setter Injection — setDependecy1 ");   //Setter Injection'ın yapıldığını göstermek amacıyla bu çıktıyı veririz.
		this.dependency1 = dependency1;
	}
	@Autowired
	public void setDependency2(Dependency2 dependency2) {
		System.out.println("Setter Injection — setDependecy2 ");   //Setter Injection'ın yapıldığını göstermek amacıyla bu çıktıyı veririz.
		this.dependency2 = dependency2;
	}
	
	public String toString() {
		return "Using " + dependency1 + " and " + dependency2;
	}
	*/
	
	//Constructor Injection
	Dependency1 dependency1;
	Dependency2 dependency2;
	
	@Autowired   //Constructor Injection yaparken buraya 'Autowired' yazmak zorunlu değildir. Yani yazmasak bile Spring bu injection işlemini otomatik olarak yapar.
	public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
		System.out.println("Constructor Injection — YourBusinessClass ");    //Constructor Injection'ın yapıldığını göstermek amacıyla bu çıktıyı veririz.
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}
	
	public String toString() {
		return "Using " + dependency1 + " and " + dependency2;
	}
	
}
@Component
class Dependency1{
	
}
@Component
class Dependency2{
	
}

@Configuration
@ComponentScan  //Direkt olarak bu pakette tarama yapacaktır.
public class DepInjectionLauncherApplication {

	public static void main(String[] args) {

		try(var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)) {
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			//Field Injection
			//System.out.println(context.getBean(YourBusinessClass.class));   //Çıktı olarak Using com.in28minutes.learn_spring_framework.examples.a1.Dependency1@52af26ee and com.in28minutes.learn_spring_framework.examples.a1.Dependency2@6fd83fc1 alırız.
			
			//Setter Injection
			//System.out.println(context.getBean(YourBusinessClass.class));   //Çıktı olarak Using com.in28minutes.learn_spring_framework.examples.a1.Dependency1@4310d43 and com.in28minutes.learn_spring_framework.examples.a1.Dependency2@54a7079e alırız.

			//Constructor Injection
			System.out.println(context.getBean(YourBusinessClass.class));     //Çıktı olarak Using com.in28minutes.learn_spring_framework.examples.a1.Dependency1@1de76cc7 and com.in28minutes.learn_spring_framework.examples.a1.Dependency2@54bff557 alırız.
			
			
		}
		
	}

}

