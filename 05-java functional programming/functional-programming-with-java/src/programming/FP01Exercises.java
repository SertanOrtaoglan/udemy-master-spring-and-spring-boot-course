package programming;

import java.util.List;

public class FP01Exercises {

	public static void main(String[] args) {
		
		
		List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
		//printOddNumbersInListFunctional(numbers);         //1.örnek için
		printCubesOfOddNumbersInListFunctional(numbers);    //5.örnek için
		
		
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
		//printAllCoursesIndividually(courses);             //2.örnek için
		//printSpringCoursesInList(courses);                //3.örnek için
		//printAtLeast4LettersCoursesInList(courses);       //4.örnek için 
		printNumberOfCharactersInEachCourseName(courses);
		
	}

	//1.örnek(tek sayıları yazdırma)
	private static void printOddNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream().filter(number -> number % 2 != 0).forEach(System.out::println);
	}
	
	//2.örnek(tüm kursları yazdırma)
	private static void printAllCoursesIndividually(List<String> courses) {
		courses.stream().forEach(System.out::println);
	}

	//3.örnek(içinde 'Spring' geçen kursları yazdırma)
	private static void printSpringCoursesInList(List<String> courses) {
		courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
		
	}
	
	//4.örnek(en az 4 harfe sahip olan kursları yazdırma)
	private static void printAtLeast4LettersCoursesInList(List<String> courses) {
		courses.stream().filter(course -> course.length() >= 4).forEach(System.out::println);
	}
	
	
	
	//6.ders(exercise 5 and exercise 6)
	
	//5.örnek(Listedeki tek sayıların kübünü yazdırma)
	private static void printCubesOfOddNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream().filter(number -> number % 2 != 0).map(number -> number * number * number).forEach(System.out::println);
	}
	
	//6.örnek(Listedeki her kurs adının karakter sayısını yazdırma)
	private static void printNumberOfCharactersInEachCourseName(List<String> courses) {
		//courses.stream().map(course -> course.length()).forEach(System.out::println);                //Listedeki her bir kursun karakter sayısı sırasıyla(alt alta olacak şekilde) ekrana yazdırılır.
		courses.stream().map(course -> course + " " + course.length()).forEach(System.out::println);   //Listedeki her bir kursun karakter sayısı, kursun adı ve bir boşluk ardından karakter sayısı olacak şekilde ekrana yazdırılır.
	}
	
	
	

}

