package com.kabera.sobersteps;

import com.kabera.sobersteps.model.Role;
import com.kabera.sobersteps.model.RoleName;
import com.kabera.sobersteps.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SoberstepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoberstepsApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return args -> {
			List<Role> roles = roleRepository.findAll();
			if(roles.size() == 0){
				roleRepository.save(new Role(RoleName.ROLE_QU));
				roleRepository.save(new Role(RoleName.ROLE_HP));
				roleRepository.save(new Role(RoleName.ROLE_MOH));
			}
		};
	}

}
