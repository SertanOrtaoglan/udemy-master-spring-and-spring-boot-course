package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpringApp {

	public static void main(String[] args) {
		
		//1: Launch a Spring Context
		
		try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
			
			//2: Configure the things that we want Spring to manage —
			//HelloWorldConfiguration — @Configuration
			//name — @Bean
			
			//3: Retrieving Beans managed by Spring
			/*
			System.out.println(context.getBean("name"));  //Almak istediğimiz bean'in('name' metotu) adı yazılır.

			System.out.println(context.getBean("age"));
			
			System.out.println(context.getBean("person"));
			
			//System.out.println(context.getBean("address"));    //Çıktı olarak Address[firstLine=Baker Street, city=London] alırız.
			System.out.println(context.getBean("address2"));     //bean'e verilen özel isim(adsress2) neyse o yazılır.(yani metotun adı olan 'address' yazılmaz!) Çıktı olarak Address[firstLine=Baker Street, city=London] alırız.
			System.out.println(context.getBean(Address.class));  //Burada bean'e class adı verdik. Yani 'Address.class' tipinden bir bean almak istiyorum demiş olduk. Çıktı olarak yine Address[firstLine=Baker Street, city=London] alırız.
			*/
			
			
			/*
			//diğer ders (ilk kısım)
			System.out.println(context.getBean("person"));              //Çıktı olarak Person[name=Ravi, age=20] alırız.
			System.out.println(context.getBean("person2MethodCall"));   //Çıktı olarak Person[name=Ranga, age=15] alırız.
			*/
			/*
			//diğer ders (ikinci kısım)
			System.out.println(context.getBean("person"));              //Çıktı olarak Person[name=Ravi, age=20, adress=Address[firstLine=Main Street, city=Utrecht]] alırız.
			System.out.println(context.getBean("person2MethodCall"));   //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Baker Street, city=London]] alırız.
			*/
			/*
			//diğer ders (üçüncü kısım)
			System.out.println(context.getBean("person"));              //Çıktı olarak Person[name=Ravi, age=20, adress=Address[firstLine=Main Street, city=Utrecht]] alırız.
			System.out.println(context.getBean("person2MethodCall"));   //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Baker Street, city=London]] alırız.
			System.out.println(context.getBean("person3Parameters"));   //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Baker Street, city=London]] alırız.
			*/
			/*
			//diğer ders (dönrdüncü kısım)
			System.out.println(context.getBean("person"));              //Çıktı olarak Person[name=Ravi, age=20, adress=Address[firstLine=Main Street, city=Utrecht]] alırız.
			System.out.println(context.getBean("person2MethodCall"));   //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Baker Street, city=London]] alırız.
			System.out.println(context.getBean("person3Parameters"));   //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Motinagar, city=Hyderabad]] alırız.
			*/
			
			/*
			//step 14 -01 dersi(spring framework tarafından yönetilen tüm bean'lerin listelenmesi)
			//context.getBeanDefinitionNames(); ---> Spring context içerisindeki tüm bean'lerin isimlerini döndürür. Ayrıca 'getBeanDefinitionCount()' operasyonu ile spring context içerisindeki toplam bean sayısını da yazdırabiliriz. Veya spring context içerisindeki belirli bir bean'i kontrol etmek istersek 'getBeanDefinition(String beanName)' operasyonunu kullanabiliriz.(kontrol etmek istediğimiz bean adını parametre olarak geçeriz)
			//'getBeanDefinitionNames()' operasyonu 'String[]' döner dolayısıyla her bir bean'i dolaşıp ekrana yazdırmak için şunu yaparız;(stream kullanarak yaptık) 
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);   //çıktı olarak spring context içerisinde olan tüm bean'ler alt alta olacak şekilde ekrana yazdırılır.(helloWorldConfiguration,name,age,address2,address3,person,person2MethodCall,person3Parameters)
			*/
			
			//step 14 -02 dersi(spring context içerisinde birden fazla eşleşen bean varsa ne olur?)
			System.out.println(context.getBean(Address.class));         //Çıktı olarak Address[firstLine=Baker Street, city=London] alırız. Çünkü 'Address' tipini kullanan bean'lerden 'address2'yi 'Primary' yaptık.
			System.out.println(context.getBean(Person.class));          //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Baker Street, city=London]] alırız. Çünkü 'Person' tipini kullanan bean'lerden 'person4Parameters'ı 'Primary' yaptık.
			System.out.println(context.getBean("person5Qualifier"));    //Çıktı olarak Person[name=Ranga, age=15, adress=Address[firstLine=Motinagar, city=Hyderabad]] alırız. Çünkü 'address3'ü qualifier yapıp '@Qualifier("address3qualifier")' adını verdik. Ardından 'person5Qualifier' bean'ine parametre olarak qualifier yaptığımız 'address3'ü geçtik --> public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) şeklinde. 
			//NOT: @Qualifier Aynı türden birden fazla bean mevcut olduğunda hangi bean'in enjekte edileceğini belirtmek için, çakışmaları çözmeye yardımcı olur.
			
		}
		
	}
	
}

