package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
//NOTE for 'record' :
//1: Java beans oluştururken ayrıntılara girmeyi ortadan kaldırır.
//2: Public accessor methods, constructor
//3: equals, hashCode ve toString otomatik olarak oluşturulur.
//4: JDK 16 ile gelmiştir.
record Person (String name, int age) { };    //bize getter setter ve constructor'ı olan bir 'Person' sınıfı oluşturur.
*/


//diğer ders ikinci kısım (3 parametreli 'Person' record'u oluşturalım)
record Person(String name, int age, Address adress) { };

//örnek alıştırma (with 'record')
record Address(String firstLine, String city) { };


@Configuration
public class HelloWorldConfiguration {
	/*
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20);
	}
	
	//
	@Bean
	public Address address() {
		return new Address("Baker Street", "London");
	}
	//
	
	//bean'imize özel isim vermek istersek;
	@Bean(name = "address2")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	*/
	
	
	/*
	//Diğer ders(ilk kısım) 
	//('person2' adında yeni bir kişi oluşturacağız ve bunu mevcut bean'leri kullanarak yapacağız. Bunun için iki farklı yol vardır. ilk yol metotların çağrılmasıdır. İkinci yol ise metot parametrelerini kullanmaktır.)
	//İlk yol olan 'metotların çağrılması' ile yaparsak; 
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20);
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age());  //name,age
	}
	*/
	
	/*
	//Diğer ders (ikinci kısım)
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean(name = "address2")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Main Street", "Utrecht"));
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address());  //name,age,address(Yukarıda oluşturduğumuz 'name()', 'age()' ve 'address()' bean'lerinin metotları çağrıldı.)
	}
	*/
	
	/*
	//Diğer ders (üçüncü kısım)
	//İkinci yol olan 'metot parametre'si ile yaparsak; (Parametre olarak 'bean' isimleri direkt yazılır.)
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean(name = "address2")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Main Street", "Utrecht"));
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address());  //name,age,address
	}
	
	@Bean
	public Person person3Parameters(String name, int age, Address address2) {  //name,age,address2(bean isimleri yazılır.)
		return new Person(name, age, address2);
	}
	*/
	
	/*
	//Diğer ders (dördüncü kısım)
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean(name = "address2")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	
	@Bean(name = "address3")
	public Address address3() {
		return new Address("Motinagar", "Hyderabad");
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Main Street", "Utrecht"));
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address());  //name,age,address
	}
	
	@Bean
	public Person person3Parameters(String name, int age, Address address3) {  //name,age,address3(bean isimleri yazılır.)
		return new Person(name, age, address3);
	}
	*/
	
	
	//step 14 -02 dersi(spring context içerisinde birden fazla eşleşen bean varsa ne olur? 'Primary/Qualifier')
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	//Burada işlem yaptık;
	@Bean(name = "address2")
	@Primary
	public Address address() {
		return new Address("Baker Street", "London");
	}
	
	@Bean(name = "address3")
	@Qualifier("address3qualifier")
	public Address address3() {
		return new Address("Motinagar", "Hyderabad");
	}
	//
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Main Street", "Utrecht"));
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address());  //name,age,address
	}
	
	@Bean
	public Person person3Parameters(String name, int age, Address address3) {  //name,age,address2(bean isimleri yazılır.)
		return new Person(name, age, address3);
	}
	
	//Burada işlem yaptık;
	@Bean
	@Primary
	public Person person4Parameters(String name, int age, Address address) {  
		return new Person(name, age, address);
	}
	
	@Bean
	public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) {  
		return new Person(name, age, address);
	}
	//
	
	
	
	
	
	
	
	
}


