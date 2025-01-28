package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
	
	@Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")          //"aopexample" paketi altındaki tüm paketler(yani "business" ve "data" paketleri)
	public void businessAndDataPackageConfig() {}
	
	@Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))")   //Sadece "aopexample" paketi altındaki "business" paketi 
	public void businessPackageConfig() {}
	
	@Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.data.*.*(..))")       //Sadece "aopexample" paketi altındaki "data" paketi
	public void dataPackageConfig() {}
	
	
	//Pointcut yapılandırmasını basitleştirmek için yapabileceğimiz bir diğer şey de "bean" kullanmaktır. Yukarıdaki gibi "execution" kullanmak yerine "bean" kullanabiliriz. "Bean" interception'larını kullanarak, "bean"in adına göre(mesela *Service*) intercept(kesme) yapabiliriz.
	
	@Pointcut("bean(*Service*)")    //Yani "Service" bean'i olan tüm bean'lere(class'lara) müdahale etmek(kesmek) istiyoruz. Sonuç olarak log'larda tüm class'lar gözükecektir çünkü "@Pointcut("bean(*Service*)")" ifadesi hem 'DataService'i(1,2) hem de 'BusinessService'i(1,2) kesecektir. NOT -> Diyelim ki sadece 'DataService' class'ını engellemek(kesmek) istiyoruz bunun için pointcut'ı "@Pointcut("bean(*dataService*)")" şeklinde tanımlamamız gerekir. Log'larda çıktı olarak sadece 'DataService1' ve 'DataService2'nin 'retrieveData()' methodunu görürüz.('BusinessService1' ve 'BusinessService2'nin methodları olan 'calculateMax()' ve 'calculateMin()' gözükmez.)
	public void allPackageConfigUsingBean() {}
	
	
}
