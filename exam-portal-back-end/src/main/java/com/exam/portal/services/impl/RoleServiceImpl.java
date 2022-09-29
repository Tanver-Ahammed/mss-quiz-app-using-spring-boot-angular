package com.exam.portal.services.impl;

import com.exam.portal.entities.Role;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.RoleRepository;
import com.exam.portal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(Long roleId) {
        return this.roleRepository.findById(roleId).orElseThrow(() ->
                new ResourceNotFoundException("Role", "id", roleId));
    }
}
