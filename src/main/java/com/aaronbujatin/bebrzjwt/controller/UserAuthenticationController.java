package com.aaronbujatin.bebrzjwt.controller;

import com.aaronbujatin.bebrzjwt.dto.SignInDto;
import com.aaronbujatin.bebrzjwt.dto.SignUpDto;
import com.aaronbujatin.bebrzjwt.dto.UserAuthenticationResponse;
import com.aaronbujatin.bebrzjwt.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

   private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
      userAuthenticationService.registerUser(signUpDto);
      return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserAuthenticationResponse> signIn(@RequestBody SignInDto signInDto){
        UserAuthenticationResponse response = userAuthenticationService.loginUser(signInDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
