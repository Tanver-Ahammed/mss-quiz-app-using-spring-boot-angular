package com.exam.portal.services;

import com.exam.portal.entities.Role;

import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

public interface RoleService {

    // get role by id
    Role getRoleById(Long roleId);

    // get all role
    List<Role> getAllRole();

}
