package com.devsuperior.dscomerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscomerce.dto.ProductDTO;

import com.devsuperior.dscomerce.services.ProductService;



@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {

        ProductDTO productDTO = service.findById(id);
        return productDTO;

    }

    @GetMapping()
    public List<ProductDTO> findAll(){
        return service.findAll();
    }
    

}
