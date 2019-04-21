package com.capgemini.springwithjunit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.springwithjunit.dao.ProductDao;
import com.capgemini.springwithjunit.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public Product findByProductId(Integer id) {
		return dao.findById(id).get();
	}
	
	public Product saveProduct(Product product) {
		return dao.save(product);
	}
	
	public void deleteById(Integer id) {
        dao.deleteById(id);
    }
	
	public List<Product> findAll() {
	    return dao.findAll();
	}
}
