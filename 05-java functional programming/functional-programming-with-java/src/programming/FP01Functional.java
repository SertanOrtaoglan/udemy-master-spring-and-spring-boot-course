package programming;

import java.util.List;

public class FP01Functional {

	public static void main(String[] args) {
		
		
		List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
		//printAllNumbersInListFunctional(numbers);
		//printEvenNumbersInListFunctional(numbers);
		printSquaresOfEvenNumbersInListFunctional(numbers);
	}
	
	
	/*
	private static void print(int number) {
		System.out.println(number);
	}

	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		//[12,9,13,4,6,2,4,12,15] elimizde bu şekilde bir liste var. Bu listeyi;
		//12
		//9
		//13
		//4
		//6
		//...
		//şekline dönüştürmek istiyoruz. Yani elimizdeki listeyi bir akışa(stream'e) dönüştürmek istiyoruz.
		//Bu işlemi şu şekilde yaparız;
		
		numbers.stream().forEach(FP01Functional::print);   //'forEach()' içerisinde 'method referansı(method reference)' dediğimiz olayı uyguladık.
	}
	*/
	
	
	
	
	//3.ders(Aynı örneği daha basit bir şekilde yaparsak;)
	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream().forEach(System.out::println);
	}
	
	
	/*
	//3.ders ikinci kısım(Çift sayıları yazdırma örneği)
	private static boolean isEven(int number) {
		return number % 2 == 0;
		
	}
	
	private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
		//Filter - Only Allow Even Number
		numbers.stream().filter(FP01Functional::isEven).forEach(System.out::println);
	}
	*/
	
	
	
	
	
	//4.ders(bir önceki derste yaptığımız çift sayıları bulma örneğini daha basit bir şekilde yaparsak;)
	private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
		//Lambda Expression('filter()' içerisinde)
		numbers.stream().filter(number -> number % 2 == 0).forEach(System.out::println);
	}
	
	
	
	
	
	//6.ders(Listedeki çift sayıların karesini yazdırma)
	private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {
		//Çift sayıların karesini almak için yani x -> x * x için 'map()' operasyonu ile 'mapping(eşleme)' yapmamız gerekir.
		numbers.stream().filter(number -> number % 2 == 0).map(number -> number * number).forEach(System.out::println);
	}

	

}

