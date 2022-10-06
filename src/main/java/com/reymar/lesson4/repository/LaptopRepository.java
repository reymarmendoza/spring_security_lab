package com.reymar.lesson4.repository;

import com.reymar.lesson4.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
