package com.reymar.lesson4.service.impl;

import com.reymar.lesson4.exception.LaptopNotFoundException;
import com.reymar.lesson4.model.Laptop;
import com.reymar.lesson4.repository.LaptopRepository;
import com.reymar.lesson4.service.LaptopService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {
	private final LaptopRepository laptopRepository;
	
	public LaptopServiceImpl(LaptopRepository laptopRepository) {
		this.laptopRepository = laptopRepository;
	}
	
	@Override
	public List<Laptop> getLaptops() {
		return laptopRepository.findAll();
	}
	
	@Override
	public Long postLaptop(Laptop laptop) {
		return laptopRepository.save(laptop)
				.getId();
	}
	
	@Override
	public Optional<Laptop> findLaptop(Long id) {
		return laptopRepository.findById(id);
	}
	
	@Override
	public Laptop updateLaptop(Long id, Laptop updatedLaptop) {
		Optional<Laptop> laptop = findLaptop(id);
		
		if (laptop.isEmpty()) throw new LaptopNotFoundException(id);
		
		Laptop oldLaptop = laptop.get();
		
		oldLaptop.setMarca(updatedLaptop.getMarca());
		oldLaptop.setModelo(updatedLaptop.getModelo());
		oldLaptop.setDueno(updatedLaptop.getDueno());
		
		return laptopRepository.save(oldLaptop);
	}
	
	@Override
	public String deleteLaptop(Long id) {
		if (!laptopRepository.existsById(id))
			throw new LaptopNotFoundException(id);
		
		laptopRepository.deleteById(id);
		return String.format("Se elimino el laptop %d", id);
	}
}
