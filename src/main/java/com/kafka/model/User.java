package com.kafka.model;

public class User {
	private String nome;
	private String cognome;
	private int age;
	private String message;
	public User() {}
	public User(String nome,String cognome,int age,String message) {
		this.age=age;
		this.cognome=cognome;
		this.nome=nome;
		this.message=message;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
