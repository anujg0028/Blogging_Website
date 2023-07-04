package com.anuj.blog.controllers;

import com.anuj.blog.exceptions.APIException;
import com.anuj.blog.payloads.JWTAuthRequest;
import com.anuj.blog.payloads.JWTAuthResponse;
import com.anuj.blog.payloads.UserDto;
import com.anuj.blog.security.JWTTokenHelper;
import com.anuj.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class authController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request) throws Exception {

        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setToken(token);
        return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.OK);
    }

    public void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch (DisabledException e){
            e.printStackTrace();
        }
        catch (UsernameNotFoundException | BadCredentialsException e) {
            e.printStackTrace();
            throw new APIException("Bad Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) throws Exception {
        UserDto user=this.userService.registerUser(userDto);
        return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
    }
}
