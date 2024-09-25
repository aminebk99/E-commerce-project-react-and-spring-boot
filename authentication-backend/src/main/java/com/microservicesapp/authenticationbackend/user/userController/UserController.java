package com.microservicesapp.authenticationbackend.user.userController;

import com.microservicesapp.authenticationbackend.jwt.JwtService;
import com.microservicesapp.authenticationbackend.user.userEntity.User;
import com.microservicesapp.authenticationbackend.user.userRepository.UserRepository;
import com.microservicesapp.authenticationbackend.user.userService.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletResponse response) {
        String token = jwtService.generateToken(user);
        Cookie cookie = new Cookie("authToken", token);
        cookie.setPath("/"); // Cookie is available across the whole domain
        cookie.setHttpOnly(true); // Prevent JavaScript access for security
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getListOfUsers());
    }
    @PostMapping("/extract")
    public String extract(@RequestParam("token") String token) {
        return jwtService.extractUsername(token);
    }
}
