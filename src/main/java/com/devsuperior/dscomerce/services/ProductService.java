package com.devsuperior.dscomerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscomerce.dto.ProductDTO;
import com.devsuperior.dscomerce.entities.Product;
import com.devsuperior.dscomerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

   @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();

        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;

    }

}
