package com.authtoken.authtoken.controller;

import com.authtoken.authtoken.dto.AuthRequest;
import com.authtoken.authtoken.dto.SignUpDto;
import com.authtoken.authtoken.dto.UserDTO;
import com.authtoken.authtoken.dto.UserResponseDTO;
import com.authtoken.authtoken.entity.UserInfo;
import com.authtoken.authtoken.repository.UserInfoRepository;
import com.authtoken.authtoken.service.JwtService;
import com.authtoken.authtoken.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Hiring Box";
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpDto signUpDto) {
        String result = service.signUp(signUpDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody AuthRequest authRequest) {
//        UserResponseDTO userResponseDTO = service.login(authRequest);
//        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        Optional<UserInfo> existingUser = userInfoRepository.findByEmail(authRequest.getEmail());
        if (authentication.isAuthenticated()){
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setEmail(existingUser.get().getEmail());
            userResponseDTO.setToken(jwtService.generateToken(existingUser.get().getFullName()));
            userResponseDTO.setRoles(Collections.singletonList(existingUser.get().getRoles().toUpperCase().replaceAll(" ","_")));

            return userResponseDTO;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(@ModelAttribute UserDTO userDTO) throws IOException {
        String answer = service.createUser(userDTO);
        return new ResponseEntity<>(answer,HttpStatus.OK);
    }

}

