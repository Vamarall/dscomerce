package com.devsuperior.dscomerce.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscomerce.dto.ProductDTO;
import com.devsuperior.dscomerce.entities.Product;
import com.devsuperior.dscomerce.repositories.ProductRepository;
import com.devsuperior.dscomerce.services.exceptions.DatabaseException;
import com.devsuperior.dscomerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso nao encontrado!"));
        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> list = productRepository.findAll(pageable);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional // Nao possui atributo "readOnly = true" pq ele faz mais que apenas uma consulta
                   // no BD
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try {
            Product entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado!");
        }
    
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso nao encontrado!");
        }
        try{
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

 

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

}
