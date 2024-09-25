package com.microservicesapp.authenticationbackend.products.productController;

import com.microservicesapp.authenticationbackend.products.productEntity.Product;
import com.microservicesapp.authenticationbackend.products.productsService.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductsService productsService;

    @GetMapping
    public List<Product> getProducts() {
        return productsService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return productsService.getProductById(id);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productsService.createProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productsService.deleteProductById(id);
    }
    @DeleteMapping
    public void deleteAllProducts() {
        productsService.deleteAllProducts();
    }

}
