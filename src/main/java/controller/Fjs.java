package controller;

import org.springframework.stereotype.Component;

@Component("fjs")
public class Fjs {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
