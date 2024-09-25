package com.microservicesapp.authenticationbackend.user.userService;

import com.microservicesapp.authenticationbackend.jwt.JwtService;
import com.microservicesapp.authenticationbackend.user.userEntity.User;
import com.microservicesapp.authenticationbackend.user.userRepository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(User user, HttpServletResponse response) {
        String username = user.getUsername();
        if (username != null){
            String jwtToken = "this is the token";
            Cookie cookie = new Cookie("authToken", jwtToken);
            cookie.setPath("/"); // Cookie is available across the whole domain
            cookie.setHttpOnly(true); // Prevent JavaScript access for security
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            return jwtToken;
        }else
            return null;

    }
}
