package com.in28minutes.learn_spring_aop.aopexample.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)           //use this annotation only on methods
@Retention(RetentionPolicy.RUNTIME)   //Runtime(runtime'da bu information'ın kullanabilir olmasını istiyoruz.)
public @interface TrackTime {   //Bu annotation'ı aynı zamanda pointcut olarak da kullanabiliriz. Bunun için "CommonPointcutConfig"de bir pointcut tanımlarız.
	
	
}
