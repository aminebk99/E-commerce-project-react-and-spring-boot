package com.microservicesapp.authenticationbackend.cart.cartService;

import com.microservicesapp.authenticationbackend.cart.cartEntity.Cart;
import com.microservicesapp.authenticationbackend.cart.cartRepository.CartRepository;
import com.microservicesapp.authenticationbackend.products.productEntity.Product;
import com.microservicesapp.authenticationbackend.products.productRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart saveCart(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Cart cart = new Cart();
            cart.setProductId(productId);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(null);
            return cartRepository.save(cart);
        }else
            throw new RuntimeException("This product Not found!");
    }
    public List<Product> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        return carts.stream()
                .map(cart -> {
                    Optional<Product> product = productRepository.findById(cart.getProductId());
                    return product.orElseThrow(() -> new RuntimeException("Product not found!"));
                })
                .collect(Collectors.toList());
    }
    public void DeleteCart(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            cartRepository.deleteById(productId);
        }else{
            throw new RuntimeException("This product Not found!");
        }
    }
    public void deleteAllCart(){
        cartRepository.deleteAll();
    }
    }



