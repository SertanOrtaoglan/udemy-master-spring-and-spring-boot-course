package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Around("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")
	//@Around("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")   //Common pointcut kullanımı
	@Around("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.trackTimeAnnotation()")    //Böylelikle sadece "trackTimeAnnotation()" özelliğine sahip olan methodları("@TrackTime") keseceğiz ve izleyeceğiz. Kodu bu şekilde çalıştırırsak log'larda "Around Aspect" için sadece 2 çıktı görürüz bunlar "@TrackTime" annotation'larını kullanan "BusinessService1.calculateMax()" methodu ile "DataService2.retrieveData()" methodudur. Yani bu iki method kesildi ve log'larda gösterildi.
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {     //Burada bu sefer 'JoinPoint' kullanmayacağız çünkü 'JoinPoint' bu method içerisinde başka bir method çalıştırmamıza izin vermez. Fakat biz burada bu method içerisinde başka bir method daha çalıştırmak istiyoruz bundan dolayı bu işlemi yapmamızı sağlayan 'ProceedingJoinPoint'i kullanacağız. Yani 'ProceedingJoinPoint'in yapmamıza izin verdiği şey, methodu yürütmektir. 'proceed()' adı verilen bir şeyi çağırabilir ve bu sayede methodu çalıştırabiliriz bu da '@Around advice'i uygulamamıza yardımcı olur.  
		//start a timer(method çalıştırılmadan önce)
		long startTimeMillis = System.currentTimeMillis();
		
		//Execute the method
		Object returnValue = proceedingJoinPoint.proceed();    //'proceedingJoinPoint.proceed()' ifadesi bir return değeri döndürür dolayısıyla return edilen değeri yazdırabilmak için bu ifadeyi bir local variable'ye atarız.  NOT -> Bu ifade['proceedingJoinPoint.proceed()'] ayrıca bir exception fırlatabilir dolayısıyla methoda 'Throwable' ekleyerek bunu belirtiriz.
		
		//Stop the timer(method çalıştırıldıktan sonra)
		long stopTimeMillis = System.currentTimeMillis();
		
		long executionDuration = stopTimeMillis - startTimeMillis;
		
		logger.info("Around Aspect - {} Method executed in {} ms", proceedingJoinPoint,executionDuration);
		
		return returnValue;   //Yukarıda 'proceedingJoinPoint.proceed()' ile gelen değeri böylelikle geri döndürmüş oluruz. NOT: Bu 'findExecutionTime()' methodumuzun return değerini başta void olarak yazmıştık ancak 'proceedingJoinPoint.proceed()' ifadesi ile bize gelen return değerinin tipi 'Object'dir. Sonuç olarak işlem sonucunda bu değeri döndüreceğimiz için 'findExecutionTime()' methodumuzun return tipini 'Object' olarak değiştiririz.
		
		//Sonuç olarak bir methodun ne kadar sürede çalıştığını öğrenmek istiyoruz. Bunun için methodun yürütülmesinden önce ve sonra işlemler yapmamız gerekir dolayısıyla @Around'u kullanmamız gerekir. Ve sonuç olarak zaman hesaplamasını yapıp çıktıyı ekrana log'larda gösterecek şekilde ayarlarız. Son olarak ise method tarafından['proceedingJoinPoint.proceed()'] return edilen değerlerin düzgün bir şekilde geri döndürüldüğünden emin olmak adına "returnValue" değerini ekrana yazdırırız.
		
		//Kodumuzu çalıştırıp log'lara bakarsak şunları görürüz;   Before Aspect...
		//														   Before Aspect...
		//														   Around Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) Method executed in 0 ms
		//														   AfterReturning Aspect...
		//														   After Aspect...
		//														   Around Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) Method executed in 6 ms
		//													       AfterReturning Aspect...
		//														   After Aspect...
		//
		//Görüldüğü üzere 2 kez "Around Aspect" yazdırılmıştır. Bunlardan birincisi DataService1'deki 'retrieveData()' methodu içindir. Yani yürütme işlemini tamamlayan ilk method 'retrieveData()'dır.("0 ms" içinde) Diğeri ise BusinessService1'deki 'calculateMax()' methodu içindir. 'calculateMax()' ise "6 ms"de yürütülmüştür.
		
		
		//Dilersek BusinessService1'deki 'calculateMax()' methodu içerisinde bir "Thread.sleep()" tanımlayabiliriz. Bu işlemi yaptıktan sonra kodu çalıştırıp log'lara bakarsak;    Around Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) Method executed in 0 ms
		//																																											Around Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) Method executed in 39 ms
		//Görüldüğü gibi DataService1'deki 'retrieveData()' methodu yine "0 ms" içinde yürütülmüştür. Ancak BusinessService1'deki 'calculateMax()' methodu "39 ms" içinde yürütülmüştür. Sebebi ise 'calculateMax()' içerisinde yazdığımız 'Thread.sleep(30)'dan kaynaklıdır. 
		
		
		
		//Aynı şekilde diyelim ki sadece DataService1'deki 'retrieveData()' içinde bir "Thread.sleep(30)" tanımlanmış olsun(yani 'calculateMax()'deki thread'i kaldırdık) şimdi kodu çalıştırıp log'lara bakarsak;  Around Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) Method executed in 35 ms
		//																																							Around Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) Method executed in 39 ms
		//Bu sefer her iki methodun'da["retrieveData()" ve "calculateMax()"] biraz daha uzun sürede yürütüldüğünü görürüz. 'retrieveData()' methodu "35 ms"de, 'calculateMax()' methodu ise "39 ms"de yürütülmüştür.
	}
	
	
	
	//Son olarak "BusinessService1 ve DataService1" için oluşturduğumuz yapının benzerini "BusinessService2 ve DataService2" ismiyle yeniden oluşturalım. Bu sefer 'calculateMax()' yerine 'calculateMin()' methodu tanımlayalım. Ardından DataService2'deki 'retrieveData()' içinde bir "Thread.sleep(30)" tanımlayalım. Bu şekilde kodu çalıştırıp log'ları incelersek;
	//Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) is called with arguments: []
	//Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) is called with arguments: []
	//Around Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) Method executed in 34 ms
	//AfterReturning Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has returned [11, 22, 33, 44, 55]
	//After Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService1.retrieveData()) has executed
	//Around Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) Method executed in 37 ms
	//AfterReturning Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has returned 55
	//After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService1.calculateMax()) has executed
	//businessService1 Value returned is 55
	//Before Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService2.calculateMin()) is called with arguments: []
	//Before Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService2.retrieveData()) is called with arguments: []
	//Around Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService2.retrieveData()) Method executed in 43 ms
	//AfterReturning Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService2.retrieveData()) has returned [111, 222, 333, 444, 555]
	//After Aspect - execution(int[] com.in28minutes.learn_spring_aop.aopexample.data.DataService2.retrieveData()) has executed
	//Around Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService2.calculateMin()) Method executed in 45 ms
	//AfterReturning Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService2.calculateMin()) has returned 111
	//After Aspect - execution(int com.in28minutes.learn_spring_aop.aopexample.business.BusinessService2.calculateMin()) has executed
	//businessService2 Value returned is 111
	
	//Görüldüğü gibi ilk olarak "DataService1 ve BusinessService1" içindeki 'retrieveData()' ve 'calculateMax()' methodları durdurulmuş ardından "DataService2 ve BusinessService2" içindeki 'retrieveData()' ve 'calculateMin()' methodları durdurulmuştur. Sonuç olarak 'LoggingAspect' class'ı içerisinde tanımladığımız "@Before, @After, @AfterReturning, @Around" annotation'larının tümü, "business" ve "data" paketleri içerisinde oluşturduğumuz tüm class'lar için geçerlidir. Yani hepsini durdurabilme işlemini başarıyla gerçekleştirmiştir.
	//Özetlersek; kodu bir kez yazarız("LoggingAspect" class'ı içerisinde yaptığımız gibi) ve kod, söz konusu paketteki birden fazla class'a uygulanabilir.("DataService1" ve "BusinessService1" class'ları ile "DataService2" ve "BusinessService2" class'ları için) Yani "AOP"nin yapmamıza izin verdiği şey budur. Bir "Advice" tanımlayabilir ve bunu birden fazla farklı katmana uygulayabiliriz.
	
	

}
