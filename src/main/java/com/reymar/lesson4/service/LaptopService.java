package com.reymar.lesson4.service;

import com.reymar.lesson4.model.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopService {
	List<Laptop> getLaptops();
	
	Long postLaptop(Laptop laptop);
	
	Optional<Laptop> findLaptop(Long id);
	
	Laptop updateLaptop(Long id, Laptop updatedLaptop);
	
	String deleteLaptop(Long id);
}
