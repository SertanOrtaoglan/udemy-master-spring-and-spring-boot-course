package com.in28minutes.learn_spring_framework.examples.f1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass {
	private SomeDependency someDependency;

	public SomeClass(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
		System.out.println("All dependency are ready!");
	}
	
	@PostConstruct  //PostConstruct annotation'ı, herhangi bir başlatma gerçekleştirmek için dependency injection'ı(bağımlılık enjeksiyonu) yapıldıktan sonra yürütülmesi gereken bir yöntemde(örneğe göre 'initialize' metotu) kullanılır. Bu yöntem('initialize') sınıf hizmete alınmadan önce çağrılmalıdır. Örnek verirsek; diyelim ki bağımlılıklar(dependency'ler) bağlanır bağlanmaz bazı başlatma mantıklarını(logic'leri) çalıştırmak istiyoruz mesela veritabanından bazı verileri almak veya bunun gibi şeyler, işte bu noktada @PostConstruct annotation'ını kullanabiliriz.  
	public void initialize() {
		someDependency.getReady();   
		
	}
	
	//NOT: Bir uygulama sonlandırılmadan önce, bir bean context'ten(bağlamdan) kaldırılmadan önce de bir şeyler yapmak isteyebiliriz. İşte bu işlem için '@PreDestroy' annotation'ını kullanırız. Yani örnek verirsek; veritabanına veya bunun gibi şeylere herhangi bir bağlantımız varsa, bunları temizleme metotu yazıp(örnekteki 'cleanup()' gibi) ardından bu 'PreDestroy' annotation'ını ekleyerek temizleme işleminde bean'i kapatabiliriz. 
	@PreDestroy  //PreDestroy annotation'ı, örneğin container tarafından kaldırılma sürecinde olduğunu bildirmek için bir geri arama bildirimi olarak bir yöntem üzerinde kullanılır. PreDestroy ile açıklanan yöntem(örnekteki 'cleanup()' gibi) genellikle tuttuğu kaynakları serbest bırakmak için kullanılır.
	public void cleanup() {
		System.out.println("Cleanup");
	}
	
}


@Component
class SomeDependency {

	public void getReady() {
		System.out.println("Some logic using SomeDependency");
	}
	
}


@Configuration
@ComponentScan  //Direkt olarak bu pakette tarama yapacaktır.
public class PrePostAnnotationsContextLauncherApplication {

	public static void main(String[] args) {

		try(var context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
		}
		
	}

}

