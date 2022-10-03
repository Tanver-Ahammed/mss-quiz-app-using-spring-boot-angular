package com.exam.portal.controller;

import com.exam.portal.dto.ApiResponse;
import com.exam.portal.dto.UserDTO;
import com.exam.portal.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin("http://localhost:4200/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(path = "/registration")
    public ResponseEntity<UserDTO> registrationUser(@RequestBody UserDTO userDTO) throws Exception {
        userDTO = this.userService.registrationUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = this.userService.findAllUsers();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDTO> getSingleUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(this.userService.getSingleUserById(userId));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getSingleUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully.", true),
                HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                               @PathVariable("userId") Long userId) {
        UserDTO updateUser = this.userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updateUser);
    }

}

