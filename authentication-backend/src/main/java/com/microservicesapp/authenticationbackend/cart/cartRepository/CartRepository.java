package com.microservicesapp.authenticationbackend.cart.cartRepository;

import com.microservicesapp.authenticationbackend.cart.cartEntity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
