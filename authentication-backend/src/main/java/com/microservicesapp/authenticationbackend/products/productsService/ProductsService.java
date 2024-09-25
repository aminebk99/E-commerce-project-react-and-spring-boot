package com.microservicesapp.authenticationbackend.products.productsService;

import com.microservicesapp.authenticationbackend.products.productEntity.Product;
import com.microservicesapp.authenticationbackend.products.ProductNotFoundException;
import com.microservicesapp.authenticationbackend.products.productRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("This product was not found!"));
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

}
