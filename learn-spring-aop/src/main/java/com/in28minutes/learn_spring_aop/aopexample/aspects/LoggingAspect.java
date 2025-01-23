package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	//Pointcut - When?
	// execution(* PACKAGE.*.*(..))  --> Belirli bir paketteki bir sınıfa yapılan tüm çağrıları engellemek istersek, bu sözdizimini kullanırız.  NOT: execution'dan sonra gelen ilk * (parantez açıldıktan sonra ilk yazılan) herhangi bir return değerini belirtir.(int,String vb.) 
	// execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))  --> business paketimiz altında bulunan tüm bean'lere giden yürütmeleri(executions) engellemek için pointcut'ı bu şekilde yazarız. 
	//@Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))")    //Bu pointcut'ı, bir method çağrılmadan önce çalıştırmak istiyoruz. Bu yüzden "@Pointcut" yerine "@Before" annotation'ını kullanacağız.
	@Before("execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))")        //"@Before" içerisine pointcut'ımızı ekleriz. Böylelikle "@Before"dan önce ilk olarak aşağıdaki method çalışır ve ardından "@Before" çalışıp gelen method çağrılarını engeller.(business paketi içerisinde bulunan bean'lerde yer alan tüm method'lara çağrılar engellenir.)
	public void logMethodCall(JoinPoint joinPoint) {       //Burada belirli bir methodun execution'ını tanımlarız. Belirli bir methodun execution'ına "JoinPoint" denir. Ve bu JoinPoint'i, method parametresi olarak ekleriz.
		//Logic - What?
		logger.info("Before Aspect - Method is called - {}", joinPoint);   //Ve ayrıntıları almak için 'joinPoint'i burada kullanabiliriz. 
	}
	
	//Yukarıdaki kodu ekledikten sonra uygulamayı çalıştırırsak log'larda şunu görürüz; "Before Aspect - Method is called - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax())" Böylelikle artık 'calculateMax()' methoduna yapılan method çağrılarını engelliyoruz.
	*/
	
	
	/*
	//Yukarıdaki method'un aynısını yazalım. Ancak bu sefer "@Before"daki pointcut'a "business" yerine "data" yazalım;
	@Before("execution(* com.in28minutes.learn_spring_aop.aopexample.data.*.*(..))")
	public void logMethodCall(JoinPoint joinPoint) {       
		//Logic - What?
		logger.info("Before Aspect - Method is called - {}", joinPoint);
	}
	
	//Şimdi kodu çalıştırıp bakarsak; log'larda şunu görürüz; "Before Aspect - Method is called - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData())" Görüldüğü gibi bu sefer "DataService1" bean'indeki 'retrieveData()' methoduna yapılan method çağrılarını engelliyoruz.
	//Sonuç olarak; sadece paket adını değiştirerek("@Before"daki pointcut içerisinde), engellenen method'ları değiştirebiliriz.
	*/
	
	
	//NOT: Hem "business" paketindeki hem de "data" paketindeki her şeyi kesmek istiyorsak, pointcut'ı @Before("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))") şeklinde yazarız.(Yani en son kısımda paket adı yazdığımız yere * koymamız yeterlidir.) Böylelikle hem "business" hem de "data" paketinde bulunan bean'lere giden method çağrılarını kesmiş oluruz.
	@Before("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")
	public void logMethodCall(JoinPoint joinPoint) {       
		//Logic - What?
		logger.info("Before Aspect - {} is called with arguments: {}", joinPoint,joinPoint.getArgs());   //Method parametresi olarak bir 'joinPoint' alıyoruz ve hangi method'ların kesildiğini log'a yazdırıyoruz. 'joinPoint' ayrıca method çağrısı hakkında birkaç ayrıntı daha belirlememize yardımcı olur. Örneğin 'getArgs()'a bakalım.
		//Kodu bu şekilde çalıştırıp bakarsak log'larda şunları görürüz;   Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
		//                                                                 Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
		//Her iki log'un son kısmında "is called with arguments: []" ifadesi vardır. Yani kesilen iki method'unda(calculateMax ve retrieveData) şu an için herhangi bir argümanı yoktur demektir. Bu yüzden boş bir dizi [] çıktı olarak ekrana yazdırılır.
	}
	
	//Şimdi kodu çalıştırıp bakarsak loglarda şunları görürüz;   Before Aspect - Method is called - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax())
	//                                                           Before Aspect - Method is called - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData())
	//Görüldüğü gibi 2 tane "aspect logging" ekrana yazdırıldı. İlk olarak "BusinessService1"in 'calculateMax()' methodu kesildi(engellendi), ardından "DataService1"in 'retrieveData()' methodu kesildi.(engellendi)
	
	
	
	
}
