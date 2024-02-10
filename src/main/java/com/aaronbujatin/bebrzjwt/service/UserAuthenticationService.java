package com.aaronbujatin.bebrzjwt.service;

import com.aaronbujatin.bebrzjwt.dto.SignInDto;
import com.aaronbujatin.bebrzjwt.dto.SignUpDto;
import com.aaronbujatin.bebrzjwt.dto.UserAuthenticationResponse;
import com.aaronbujatin.bebrzjwt.entity.Role;
import com.aaronbujatin.bebrzjwt.entity.User;
import com.aaronbujatin.bebrzjwt.exception.ResourceNotFoundException;
import com.aaronbujatin.bebrzjwt.jwt.JwtService;
import com.aaronbujatin.bebrzjwt.repository.RoleRepository;
import com.aaronbujatin.bebrzjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserAuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void registerUser(SignUpDto signUpDto){
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = User.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .username(signUpDto.getUsername())
                .roles(Collections.singleton(role))
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        userRepository.save(user);
    }

    public UserAuthenticationResponse loginUser(SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));
        String token = jwtService.generateToken(authentication.getName());
        return new UserAuthenticationResponse(token);
    }
}
