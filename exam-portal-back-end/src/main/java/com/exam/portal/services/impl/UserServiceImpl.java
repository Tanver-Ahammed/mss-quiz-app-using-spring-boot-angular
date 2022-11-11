package com.exam.portal.services.impl;

import com.exam.portal.config.AppConstants;
import com.exam.portal.dto.UserDTO;
import com.exam.portal.email.EmailSenderService;
import com.exam.portal.entities.Role;
import com.exam.portal.entities.User;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.RoleRepository;
import com.exam.portal.repositories.UserRepository;
import com.exam.portal.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private EmailSenderService emailSenderService;

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
        user.setRoles(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationCode = RandomString.make(64);
        user.setVerificationCode(verificationCode);
        user.setEnable(false);
        boolean isSendMail = this.sendOtpEmail(user, "Active Account");
        if (!isSendMail)
            throw new RuntimeException("This Mail is Not Valid!!!");
        user = this.userRepository.save(user);
        return this.userToUserDTO(user);
    }

    @Override
    public void activeAccountUsingOPT(UserDTO userDTO) {
        User user = this.userRepository.findUserByUsername(userDTO.getUsername());
        if (user == null)
            throw new RuntimeException("This user not found");
        if (user.getVerificationCode().equals(userDTO.getVerificationCode())) {
            Set<Role> roles = new HashSet<>();
            Role role = this.roleRepository.findById(3L).orElseThrow(() ->
                    new ResourceNotFoundException("Role", "id", 3L));
            roles.add(role);
            user.setRoles(roles);
            String verificationCode = RandomString.make(6);
            user.setVerificationCode(verificationCode);
            user.setEnable(true);
            this.userRepository.save(user);
        } else
            throw new RuntimeException("OTP not matching...");
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
    public UserDTO getSingleUserById(Long userId) {
        return this.userToUserDTO(this.getUserById(userId));
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
        User user = this.getUserById(userId);
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        String verificationCode = RandomString.make(6);
        user.setVerificationCode(verificationCode);
        return this.userToUserDTO(this.userRepository.save(user));
    }

    @Override
    public UserDTO forgetPassword(UserDTO userDTO) {
        User user = this.userRepository.findByEmail(userDTO.getEmail());
        if (user == null)
            throw new RuntimeException("This user not found");
        this.sendOtpEmail(user, "Set Password");
        return this.userToUserDTO(user);
    }

    @Override
    public void forgetPasswordSet(UserDTO userDTO) {
        User user = this.userRepository.findByEmail(userDTO.getEmail());
        if (user == null)
            throw new RuntimeException("This user not found");
        if (user.getVerificationCode().equals(userDTO.getVerificationCode())) {
            user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
            String verificationCode = RandomString.make(6);
            user.setVerificationCode(verificationCode);
            this.userRepository.save(user);
        } else
            throw new RuntimeException("OTP not matching...");
    }

    public UserDTO updateUserRoleBySuperAdmin(UserDTO userDTO) {
        User user = this.getUserById(userDTO.getId());
        Long id = userDTO.getRoles().iterator().next().getId();
        Role role = this.roleService.getRoleById(id);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return this.userToUserDTO(this.userRepository.save(user));
    }

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    // send email for verification
    private boolean sendOtpEmail(User user, String status) {
        String subject = "Please, Use This OTP for forget password";
        String emailContent = "<p><b>Dear " + user.getUsername() + ",</b></p>";
        if (status.equals("Set Password"))
            emailContent += "Please put the opt in the app and " + status + ":<br>"
                    + "<h1>OTP: <b>" + user.getVerificationCode() + "</b></h1>";
        else if (status.equals("Active Account")) {
            String siteURL = AppConstants.frontEndLink + "/active/account/" + user.getUsername() + "/" + user.getVerificationCode();
            emailContent += "Please click the link below to verify your registration and Set Password:<br>"
                    + "<h1><a href=\"" + siteURL + "\" target=\"_self\">VERIFY</a></h1>";
        }
        emailContent += "Thank you,<br>"
                + "MSS - ICT Quiz App.";
        try {
            this.emailSenderService.sendEmailWithoutAttachment(user.getEmail(), subject, emailContent);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
