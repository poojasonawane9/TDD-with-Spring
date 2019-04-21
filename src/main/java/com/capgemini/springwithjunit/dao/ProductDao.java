package com.capgemini.springwithjunit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.springwithjunit.entity.Product;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
