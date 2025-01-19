package com.in28minutes.learn_spring_framework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learn_spring_framework.game")  //'Component' annotation'ını eklediğimiz 'PacmanGame' class'ının olduğu paketin adını(com.in28minutes.learn_spring_framework.game) buraya 'ComponentScan'e gireriz. Sonuç olarak bu paket içerisinde hangi 'Component' annotation'ları varsa Spring'in onlar için bir bean oluşturmasını isteriz.
public class GamingAppLauncherApplication {
	/*('Component' ile bunu iptal etmiş oluruz.)
	@Bean
	public GamingConsole game() {
		var game = new PacmanGame();
		return game;
	}
	*/
	
	/*('GameRunner' class'ına da 'Component' annotation'ı ekleyerek burayı da iptal etmiş olduk.)
	@Bean
	public GameRunner gameRunner(GamingConsole game) {   //'Component' sayesinde 'PackmanGame' class'ının bir örneği Spring tarafından oluşturuldu ve Spring bu örneği 'GamingConsole'a otomatik olarak bağladı.'auto-wiring(otomatik bağlama)' özelliği sayesinde.
		System.out.println("Parameter: "+ game);         //'Component'in gelip gelmediğini test etmek için bu kodu yazarsak çıktı olarak 'Parameter: com.in28minutes.learn_spring_framework.game.PacmanGame@67c33749'i aldığımızı görürüz.
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}
	*/
	
	public static void main(String[] args) {

		try(var context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {
			
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
			
		}
		
	}

}

