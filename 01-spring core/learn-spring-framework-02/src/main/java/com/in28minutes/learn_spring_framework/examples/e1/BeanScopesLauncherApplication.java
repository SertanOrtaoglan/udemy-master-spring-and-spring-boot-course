package com.in28minutes.learn_spring_framework.examples.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass {
	
}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {
	
}


@Configuration
@ComponentScan  //Direkt olarak bu pakette tarama yapacaktır.
public class BeanScopesLauncherApplication {

	public static void main(String[] args) {

		try(var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			System.out.println(context.getBean(NormalClass.class));    //Çıktı olarak NormalClass@169e6180 aldık.
			System.out.println(context.getBean(NormalClass.class));    //Çıktı olarak NormalClass@169e6180 aldık. 
			System.out.println(context.getBean(NormalClass.class));    //Çıktı olarak NormalClass@169e6180 aldık.
			//Özetlersek; context'ten 'NormalClass' bean'inin örneğini(instance) almak istediğimizde bize hep aynı örneği(NormalClass@169e6180) verdiğini görürüz.
			
			System.out.println(context.getBean(PrototypeClass.class));  //Çıktı olarak PrototypeClass@35aea049 aldık.
			System.out.println(context.getBean(PrototypeClass.class));  //Çıktı olarak PrototypeClass@7205765b aldık.
			System.out.println(context.getBean(PrototypeClass.class));  //Çıktı olarak PrototypeClass@47987356 aldık.
			System.out.println(context.getBean(PrototypeClass.class));  //Çıktı olarak PrototypeClass@22ef9844 aldık.
			//Özetlersek; context'ten 'PrototypeClass' bean'inin örneğini(instance) almak istediğimizde bize her defasında farklı bir örnek verdiğini görürüz.(hashcode'ları hep değişiyor) Yani bu 4 instance'ın(örneğin) hepsi birbirinden farklıdır. Sonuç olarak bu 'PrototypeClass'ı için her defasında yeni bir bean alırız.
			
			//Genel olarak özetlersek; Her zaman 'NormalClass'ın aynı instance'ını(örneğini) çıktı olarak alırız yani döndürülen 'NormalClass' örneği(instance) her defasında aynıdır ve değişmez. Ancak 'PrototypeClass'ı için bakarsak her defasında farklı örnekleri(instance) çıktı olarak alırız.(Yukarıda görüldüğü gibi)
			//NOT—> Spring framework'te oluşturulan tüm bean'ler default olarak singleton'dır. Yani temel olarak ne zaman bir bean istersek o bean'in aynı instance(örnek)'ı geri döndürülür.('NormalClass'ta olduğu gibi) Ancak yukarıda olduğu gibi(PrototypeClass'ta) bir bean'e her başvurulduğunda farklı bir bean instance'ı(örneği) oluşturulmasını istiyorsak o zaman Prototype'ı tercih etmeliyiz.'@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)' diyerek.
			
			
		}
		
	}

}

