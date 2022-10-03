package com.exam.portal.controller;

import com.exam.portal.config.JwtUtils;
import com.exam.portal.dto.UserDTO;
import com.exam.portal.model.JwtRequest;
import com.exam.portal.model.JwtResponse;
import com.exam.portal.services.impl.UserDetailsServiceImpl;
import com.exam.portal.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200/")
public class AuthenticatedController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserServiceImpl userService;

    // generate token
    @PostMapping(path = "/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException exception) {
            exception.printStackTrace();
            throw new Exception("User Not Found!!");
        }

        // authenticated user
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String jwtToken = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwtToken));

    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("User Disable!!");
        } catch (BadCredentialsException exception) {
            throw new Exception("Invalid Credential: " + exception.getMessage());
        }
    }

    // return the details of the current user
    @GetMapping(path = "/current-user")
    public UserDTO getCurrentUser(Principal principal) {
        return this.userService.getUserByUsername(principal.getName());
    }

    // forget password
    @PostMapping(path = "/forget/password")
    public ResponseEntity<UserDTO> forgetPassword(@RequestBody UserDTO userDTO) throws Exception {
        userDTO = this.userService.forgetPassword(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // forget password set
    @PostMapping(path = "/forget/password/set")
    public ResponseEntity<UserDTO> forgetPasswordSet(@RequestBody UserDTO userDTO) throws Exception {
        this.userService.forgetPasswordSet(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
