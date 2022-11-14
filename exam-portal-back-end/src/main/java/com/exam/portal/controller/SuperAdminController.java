package com.exam.portal.controller;

import com.exam.portal.dto.UserDTO;
import com.exam.portal.entities.Role;
import com.exam.portal.entities.User;
import com.exam.portal.services.impl.RoleServiceImpl;
import com.exam.portal.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@RestController
@RequestMapping(path = "/super/admin")
@CrossOrigin("http://localhost:4200/")
public class SuperAdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(path = "/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(this.roleService.getAllRole());
    }

    @PutMapping(path = "update")
    public ResponseEntity<UserDTO> updateUserRoleBySuperAdmin(@RequestBody UserDTO userDTO, Principal principal) {
        return ResponseEntity.ok(this.userService.updateUserRoleBySuperAdmin(userDTO, principal));
    }

}
