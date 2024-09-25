package com.microservicesapp.authenticationbackend.products.productRepository;

import com.microservicesapp.authenticationbackend.products.productEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
