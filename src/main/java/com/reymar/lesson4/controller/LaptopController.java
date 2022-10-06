package com.reymar.lesson4.controller;

import com.reymar.lesson4.model.Laptop;
import com.reymar.lesson4.service.LaptopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/laptop")
@RestController
public class LaptopController {
	private final Logger log = LoggerFactory.getLogger(LaptopController.class);
	private final LaptopService laptopService;
	
	public LaptopController(LaptopService laptopService) {
		this.laptopService = laptopService;
	}
	
	@GetMapping
	ResponseEntity<List<Laptop>> getLaptops() {
		log.trace("Se recibio una peticion get");
		return ResponseEntity.ok(laptopService.getLaptops());
	}
	
	@PostMapping
	ResponseEntity<Long> postLaptop(@RequestBody Laptop laptop) {
		log.trace("Se recibio una peticion post");
		return ResponseEntity.ok(laptopService.postLaptop(laptop));
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Laptop> putLaptop(
			@PathVariable Long id,
			@RequestBody Laptop updatedLaptop
	) {
		log.trace("Se recibio una peticion put");
		return ResponseEntity.ok(laptopService.updateLaptop(id, updatedLaptop));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteLaptop(@PathVariable Long id) {
		return ResponseEntity.ok(laptopService.deleteLaptop(id));
	}
}
