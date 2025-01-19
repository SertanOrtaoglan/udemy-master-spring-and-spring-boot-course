package optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlayingWithOptional {

	public static void main(String[] args) {
		List<String> fruits = List.of("apple", "banana", "mango");
		
		/*
		//Listede 'b' harfi ile başlayan ilk meyvenin adını bulma
		Predicate<? super String> predicate = fruit -> fruit.startsWith("b");
		Optional<String> optional = fruits.stream().filter(predicate).findFirst();    //'findFirst()' operasyonu Optional'dır. Yani örneğimizde 'b' ile başlayan meyve olabilir de olmayabilir de dolayısıyla Optional'dır.  
		//Not: 'Optional' null olmayan bir değer içerebilen veya içermeyen bir kapsayıcı nesnesidir. Bir değer mevcutsa, bu belirli değeri döndürür. Hiçbir değer yoksa, nesne(veya Optional nesne) boş olarak kabul edilir.
		
		System.out.println(optional);               //Çıktı olarak 'Optional[banana]' alırız.
		System.out.println(optional.isEmpty());     //Çıktı olarak 'false' alırız.
		System.out.println(optional.isPresent());   //Çıktı olarak 'true' alırız.
		System.out.println(optional.get());         //Çıktı olarak 'banana' alırız.
		*/
		
		
		//Peki listede 'c' harfi ile başlayan ilk meyvenin adını bulmaya çalışırsak ne olur? denersek;
		Predicate<? super String> predicate = fruit -> fruit.startsWith("c");
		Optional<String> optional = fruits.stream().filter(predicate).findFirst();
		
		System.out.println(optional);               //Çıktı olarak 'Optional.empty' alırız. Çünkü liste içerisinde 'c' harfi ile başlayan hiçbir meyve yoktur. Yani 'optional' nesnesinin başına yazdığımız Optional class'ımız(Optional<String>) boştur dolayısıyla 'empty' döner. Sonuç olarak hiçbir değer olmadığı için 'null' almak yerine başta tanımlanan 'Optional<String>' sayesinde 'Optional.empty' çıktısını alırız.('Optional' kullanmanın avantajı budur yani uygulama exception fırlatmaz.).
		System.out.println(optional.isEmpty());     //Çıktı olarak 'true' alırız.
		System.out.println(optional.isPresent());   //Çıktı olarak 'false' alırız.
		System.out.println(optional.get());         //Çıktı olarak ' java.util.NoSuchElementException: No value present' Exception'ı fırlatılır. Çünkü verilecek bir değer yoktur.
		
		//Özetlersek; sahip olduğumuz değerlerden birinin 'null' olma ihtimali olduğunda, 'Optional'ı kullanmamız önerilir.
		
		
		//'Optional' ile bir değer yaratmak istersek 'Optional.of()' ile yaparız. Örneğin;
		Optional.of("banana");  
		//Daha sonra bu değeri bir yerel değişkene atayabiliriz.
		Optional<String> fruit = Optional.of("banana");
		
		
		//Ayrıca 'boş(empty)' bir değeri göstermek istersek bunu da yapabiliriz;
		Optional<String> empty = Optional.empty();    //Bu işlem bize 'boş(empty)' değer döndürür.
		//Sonuç olarak boş değerleri göstermek için 'null' kullanmak yerine Java 8 ile birlikte gelen 'Optional'ı tercih ederiz.
		
		
		
	}

}
