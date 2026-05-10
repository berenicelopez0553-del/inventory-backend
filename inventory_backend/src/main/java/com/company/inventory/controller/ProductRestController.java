package com.company.inventory.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	@Autowired
	private IProductService service;
	/**
	 * Get all products
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> searchProducts(){
		ResponseEntity<ProductResponseRest> response=service.search();
		return response;
		
	}
	/**
	 * get products by id
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchProductsById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response=service.searchById(id);
		return response;
		
	}
	/**
	 * save products
	 * @return
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(@RequestBody Product product){
		ResponseEntity<ProductResponseRest> response=service.save(product);
		return response;
		
	}
	/**
	 * Update products
	 * @param product
	 * @param id
	 * @return
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(@RequestBody Product product,@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response=service.update(product,id);
		return response;	
	
}
	/**
	 * delte product
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> delete(@PathVariable Long id){
		
		ResponseEntity<ProductResponseRest> response = service.deleteById(id);
		return response;
	}
}
