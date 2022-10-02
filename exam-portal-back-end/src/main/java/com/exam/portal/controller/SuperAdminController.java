package com.exam.portal.controller;

import com.exam.portal.entities.Role;
import com.exam.portal.services.impl.RoleServiceImpl;
import com.exam.portal.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
