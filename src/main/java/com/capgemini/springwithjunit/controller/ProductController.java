package com.capgemini.springwithjunit.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.springwithjunit.entity.Product;
import com.capgemini.springwithjunit.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@RequestMapping("/save")
	public Product saveNewProduct(Product product) {
		return service.saveProduct(product);
	}
	
	@GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Optional<Product> product = service.findByProductId(id);
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(product.get());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        if (!service.findByProductId(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable(value = "id") Integer productId, @Valid @RequestBody Product productrDetails) {
            Product product = service.findByProductId(productId);

        product.setProductId(productrDetails.getProductId());
        product.setProductName(productrDetails.getProductName());
        final Product updatedProduct = service.saveProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }
}
