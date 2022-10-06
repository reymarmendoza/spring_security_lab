package com.reymar.lesson4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modelo;
	private String dueno;
	
	public Laptop() {
	}
	
	public Laptop(String marca, String modelo, String dueno) {
		this.marca = marca;
		this.modelo = modelo;
		this.dueno = dueno;
	}
	
	public Laptop(Long id, String marca, String modelo, String dueno) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.dueno = dueno;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getDueno() {
		return dueno;
	}
	
	public void setDueno(String dueno) {
		this.dueno = dueno;
	}
}
