package com.devsuperior.dscomerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscomerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

} 