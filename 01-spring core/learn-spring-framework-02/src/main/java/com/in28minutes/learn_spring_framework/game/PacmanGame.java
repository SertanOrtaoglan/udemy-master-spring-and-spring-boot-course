package com.in28minutes.learn_spring_framework.game;

import org.springframework.stereotype.Component;

@Component    //Bu annotation sayesinde 'PacmanGame' class'ının bir örneği Spring tarafından yaratılır.
public class PacmanGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("up");
	}

	@Override
	public void down() {
		System.out.println("down");
	}

	@Override
	public void left() {
		System.out.println("left");
	}

	@Override
	public void right() {
		System.out.println("right");
	}

}
