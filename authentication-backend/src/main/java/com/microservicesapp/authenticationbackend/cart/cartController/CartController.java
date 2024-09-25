package com.microservicesapp.authenticationbackend.cart.cartController;

import com.microservicesapp.authenticationbackend.cart.cartEntity.Cart;
import com.microservicesapp.authenticationbackend.cart.cartService.CartService;
import com.microservicesapp.authenticationbackend.products.productEntity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    @GetMapping
    public ResponseEntity<List<Product>> getCart() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }
    @PostMapping("/{productId}")
    public ResponseEntity<Cart> addCart(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(cartService.saveCart(productId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping
    public void deleteAllCard(){
        cartService.deleteAllCart();
    }
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.DeleteCart(id);
    }
}
