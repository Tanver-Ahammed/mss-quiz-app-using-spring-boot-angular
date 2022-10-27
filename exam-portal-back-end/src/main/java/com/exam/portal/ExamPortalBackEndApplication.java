package com.exam.portal;

import com.exam.portal.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@SpringBootApplication
public class ExamPortalBackEndApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalBackEndApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1L, "SUPER_ADMIN"));
//        roles.add(new Role(2L, "ADMIN"));
//        roles.add(new Role(3L, "NORMAL"));
//
//        User user = new User();
//        user.setId(1L);
//        user.setStudentId("Devloper");
//        user.setFirstName("Aminul");
//        user.setLastName("Bari");
//        user.setUsername("Aminul_Bari");
//        user.setEmail("tanvermbstuit16@gmail.com");
//        user.setPhone("+8801828586035");
//        user.setAbout("Java Developer");
//        user.setEnable(true);
//        user.setVerificationCode("HGKjkn");
//        user.setPassword("12345");
//        user.setRoles(roles);
//
//        this.userRepository.save(user);
//
//    }
}
