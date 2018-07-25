package fanshe;

import java.io.Serializable;
//,Serializable
public class Animal implements IAnimal {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}