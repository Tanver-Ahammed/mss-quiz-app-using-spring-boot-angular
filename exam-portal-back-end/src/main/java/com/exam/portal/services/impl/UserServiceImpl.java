package com.exam.portal.services.impl;

import com.exam.portal.dto.RoleDTO;
import com.exam.portal.dto.UserDTO;
import com.exam.portal.entities.Role;
import com.exam.portal.entities.User;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.RoleRepository;
import com.exam.portal.repositories.UserRepository;
import com.exam.portal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registrationUser(UserDTO userDTO) throws Exception {
        User user = this.modelMapper.map(userDTO, User.class);
        User localUser = this.userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (localUser != null)
            throw new Exception("Username Or Email Duplicate!!!");

        Set<Role> roles = new HashSet<>();
        Role role = this.roleRepository.findById(2L).orElseThrow(() ->
                new ResourceNotFoundException("Role", "id", 2L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roles.add(role);
        user.setRoles(roles);
        user.setEnable(true);
        user = this.userRepository.save(user);
        return this.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", username, -1));
        user.setPassword(null);
        return this.userToUserDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.delete(this.getUserById(userId));
    }

    // get user by id
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return this.userToUserDTO(this.userRepository.save(user));
    }

    public UserDTO userToUserDTO(User user) {
        Set<RoleDTO> roleDTOS = user.getRoles().stream().map(role ->
                        this.modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toSet());
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setRoleDTOS(roleDTOS);
        return userDTO;
    }


}
