package com.company.inventory.services;

import org.springframework.http.ResponseEntity;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;

public interface IProductService {
    ResponseEntity<ProductResponseRest> search();
    ResponseEntity<ProductResponseRest> searchById(Long id);
    ResponseEntity<ProductResponseRest> save(Product product);
    ResponseEntity<ProductResponseRest> update(Product product, Long id);
    ResponseEntity<ProductResponseRest> deleteById(Long id);
}
