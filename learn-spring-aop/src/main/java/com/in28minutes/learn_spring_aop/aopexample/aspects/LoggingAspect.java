package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	//"@Before" annotation
	//Pointcut - When?
	// execution(* PACKAGE.*.*(..)) 
	//NOT: Hem "business" paketindeki hem de "data" paketindeki her şeyi kesmek istiyorsak, pointcut'ı @Before("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))") şeklinde yazarız.(Yani en son kısımda paket adı yazdığımız yere * koymamız yeterlidir.) Böylelikle hem "business" hem de "data" paketinde bulunan bean'lere giden method çağrılarını kesmiş oluruz.
	//@Before("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")
	@Before("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")   //Pointcut'ları tanımladığımız ortak bir pointcut class'ı oluşturduk(CommonPointcutConfig) ve içerisinde tanımladığımız pointcut'ları buraya ekliyoruz. Böylelikle ilerde bir paket adı vs. değişimi yaptığımızda bu class içerisinde tanımladığımız her pointcut'ı teker teker değiştirmekle uğraşmadan işlemi merkezi bir noktadan yönetmiş oluyoruz.
	//@Before("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()")    //"Bean" için örnek
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {       
		//Logic - What?
		logger.info("Before Aspect - {} is called with arguments: {}", joinPoint,joinPoint.getArgs());   //Method parametresi olarak bir 'joinPoint' alıyoruz ve hangi method'ların kesildiğini log'a yazdırıyoruz. 'joinPoint' ayrıca method çağrısı hakkında birkaç ayrıntı daha belirlememize yardımcı olur. Örneğin 'getArgs()'a bakalım.
		//Kodu bu şekilde çalıştırıp bakarsak log'larda şunları görürüz;   Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
		//                                                                 Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
		//Her iki log'un son kısmında "is called with arguments: []" ifadesi vardır. Yani kesilen iki method'unda(calculateMax ve retrieveData) şu an için herhangi bir argümanı yoktur demektir. Bu yüzden boş bir dizi [] çıktı olarak ekrana yazdırılır.
	}
	
	
	//"@After" annotation
	//@After("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")  //Pointcut - WHEN
	@After("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")  //Pointcut - WHEN
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {       
		//Logic - WHAT
		logger.info("After Aspect - {} has executed", joinPoint);
		//Kodu bu şekilde çalıştırıp bakarsak(her şeyin başarılı bir şekilde çalıştığı durum) log'larda şunları görürüz;   Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
		//                                                                 												   Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
		//														           												   After Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has executed
		//                                                                 												   After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has executed
		                                                                   
		//Yani artık yalnızca önceki method çağrılarını değil("@Before" ile gelen), sonraki method çağrılarını da görürüz.("@After" ile gelen)
		
		
		//"@After" ile ilgili tek sorun; pointcut içerisindeki method bir exception fırlatıyor olsa bile(örneğin 'calculateMax()' methodu), "@After" ile tanımlanan 'logMethodCallAfterExecution()' methodu yürütülür ve çıktısı ekrana(log'a) yazdırılır. Sonuç olarak "@After"ın hem başarılı yürütmeler hem de exception fırlatan yürütmeler için çağrıldığını bilmemiz gerekir.
		//Kodu BusinessService1'deki 'calculateMax()'i exception fırlatacak hale getirip çalıştırırsak log'larda şunları görürüz;  Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
		//																														   Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
		//																														   After Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has executed
		//																														   After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has executed
		//
		//																														   java.lang.RuntimeException: Something Went Wrong
		
		//Görüldüğü gibi fırlatılan bir exception olmasına rağmen(BusinessService1'deki 'calculateMax()' methodu) buradaki "logMethodCallAfterExecution" methodu başarıyla yürütüldü ve çıktısını log olarak ekrana yazdırdı.[After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has executed] Ardından exception fırlatıldı. Exception mesajı[java.lang.RuntimeException: Something Went Wrong] ve detayları ekrana yazdırıldı. 
	}


	//"@AfterThrowing" annotation
	//@AfterThrowing(pointcut = "execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))", throwing = "exception")   //Burada ilk olarak pointcut'ı tanımladık ardından throwing = "exception" dedik. Böylelikle bir exception fırlatıldığında ve uygulama durdurulduğunda, exception'ın türü neyse bu isimde bir değişkene("excepiton") eşlenecektir.
	@AfterThrowing(pointcut = "com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()", throwing = "exception")
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		//Logic - WHAT
		logger.info("AfterThrowing Aspect - {} has thrown an exception {}", joinPoint,exception);
		//BusinessService1'deki 'calculateMax()'i exception fırlatacak hale getirip uygulamayı ayağa kaldırırsak çıktı olarak;  Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
		//																														Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
		//																														After Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has executed
		//																														AfterThrowing Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has thrown an exception {}
		//
		//																														java.lang.RuntimeException: Something Went Wrong
		//																																at com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax(BusinessService1.java:22) ~[main/:na]
		//
		//																														After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has executed
		//
		//																														java.lang.RuntimeException: Something Went Wrong
		//																																at com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax(BusinessService1.java:22) ~[main/:na]
		
		
		//Bu çıktıyı ekranda görürüz. Yani "AfterThrowing Aspect"den hemen sonra bir exception fırlatılmış[java.lang.RuntimeException: Something Went Wrong] ardından son olarak "After Aspect" yürütülmüş ve tekrardan aynı exception mesajı[java.lang.RuntimeException: Something Went Wrong] ekrana yazdırılmıştır. Burada "AfterThrowing Aspect"in olduğu yere odaklanırsak ifade bize exception fırlatan method'un BusinessService1'deki 'calculateMax()' methodu olduğunu söyler. Alt satırlarda ise fırlatılan exception ile ilgili detaylı bilgi verilmiştir.
		//Özetlersek; bu "logMethodCallAfterException" methodu yalnızca exception fırlatılan durumlarda yürütülür. Örneğimizde BusinessService1'in 'calculateMax()' methodunda exception fırlatıldığı için buradaki "logMethodCallAfterException" methodu yürütüldü ve çıktısını log'a yazdırdı.("DataService1"deki "retrieveData()" methodu herhangi bir exception fırlatmadığı için(yani düzgün bir şekilde çalıştığından) buradaki "logMethodCallAfterException" methodu onun için herhangi bir şey yazdırmadı ve sonuç olarak bu method("logMethodCallAfterException") bir kez yürütülmüş oldu.(BusinessService1'deki "calculateMax()" için yürütüldü.)
	}
	
	
	//"@AfterReturning" annotation
	//@AfterReturning(pointcut = "execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))", returning = "resultValue")   //Burada ilk olarak pointcut'ı tanımladık ardından returning = "resultValue" dedik. Böylelikle pointcut'tan bize return edilen değeri(mesela "55") aspect'imizin method imzasında[logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue)] kullanırken vereceğimiz değişken adını returning ile "resultValue" olarak tanımlamış olduk.
	@AfterReturning(pointcut = "com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()", returning = "resultValue")
	public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue) {
		//Logic - WHAT
		logger.info("AfterReturning Aspect - {} has returned {}", joinPoint,resultValue);
		//Kodu bu şekilde çalıştırıp bakarsak(her şeyin başarılı bir şekilde çalıştığı durum) log'larda şunları görürüz;  Before Aspect ...
		//																												  Before Aspect ...
		//																												  AfterReturning Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has returned [11, 22, 33, 44, 55]
		//																												  After Aspect...
		//																												  AfterReturning Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has returned 55
		//																												  After Aspect...
		//
		//Görüldüğü gibi pointcut içerisinde bulunan tüm method'lar(yani DataService1'deki 'retrieveData()' methodu ve BusinessService1'deki calculateMax()' methodu) başarıyla çalıştığı için ikisinin de log'ları(AfterReturning Aspect...) ekrana yazdırıldı. Yani bu iki method herhangi bir exception fırlatmadığı için yukarıdaki "logMethodCallAfterSuccessfulExecution" methodumuz her ikisi için de başarıyla yürütüldü. NOT: Eğer pointcut'daki method'lardan herhangi biri exception fırlatıyor olsaydı buradaki "logMethodCallAfterSuccessfulExecution" methodumuz, o exception fırlatan method için yürütülmezdi!
	}

	
	
}
