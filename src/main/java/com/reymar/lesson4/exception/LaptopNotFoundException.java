package com.reymar.lesson4.exception;

public class LaptopNotFoundException extends RuntimeException {
	public LaptopNotFoundException(Long id) {
		super(String.format("No se encontro el laptop con id %d", id));
	}
}
