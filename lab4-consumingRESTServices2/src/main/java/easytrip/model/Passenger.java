package easytrip.model;

import java.util.Objects;

public class Passenger implements Comparable<Passenger>{

	// Attributes (Private)
	private String id;
	private String name;
	private String surname;
	private Gender gender;
	private Integer age;
	
	public Passenger() {}
	
	// Builder C1: Receive all the parameters and create a Passenger object
	public Passenger(String id, String name, String surnames, Gender gender, Integer age) {
		this.id = id;
		this.name = name;
		this.surname = surnames;
		this.gender = gender;
		this.age = age;
	}

	// Getters - Setters: Methods for all the attributes
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// Equality criteria: All the passengers are different from each others depending on the id
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		return Objects.equals(id, other.id);
	}
	
	// Sorting criteria: All the passengers are sorted depending on the id
	@Override
	public int compareTo(Passenger p) {
		Integer a = Integer.parseInt(this.id.substring(1));
		Integer b = Integer.parseInt(p.getId().substring(1));
		return a.compareTo(b);
	}

	// String format: Each passenger is represented with all the values
	@Override
	public String toString() {
		return String.format("(%s, %s, %s, %s, %s)", id, name, surname, gender, age);
	}
	
}
