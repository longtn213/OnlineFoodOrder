package com.DragonSouth.controller;

import com.DragonSouth.configure.JwtProvider;
import com.DragonSouth.model.Cart;
import com.DragonSouth.model.User;
import com.DragonSouth.repository.CartRepository;
import com.DragonSouth.repository.UserRepository;
import com.DragonSouth.request.LoginRequest;
import com.DragonSouth.response.AuthResponse;
import com.DragonSouth.service.CustomerUserDetailsService;
import com.DragonSouth.type.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserDetailsService customerUserDetailsService;

    private final CartRepository cartRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
        User isEmailExist =userRepository.findByEmail(user.getEmail());
        if(isEmailExist != null){
            throw new RuntimeException("Email already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setRole(savedUser.getRole());
        authResponse.setMessage("Register successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String jwt = jwtProvider.generateToken(auth);
        AuthResponse authResponse = new AuthResponse();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successfully");
        authResponse.setRole(USER_ROLE.valueOf(role));


        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username....");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
