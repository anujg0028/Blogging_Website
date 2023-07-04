package com.anuj.blog;

import com.anuj.blog.config.AppConstants;
import com.anuj.blog.entities.Role;
import com.anuj.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	//used to run thing which we want to run firstly when an application start
	@Override
	public void run(String... args) throws Exception {

		//if no role is present in the DB then add 2 role in DB otherwise ignore it
		try{
			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_ADMIN);
			role1.setName("ROLE_ADMIN");

			Role role2 = new Role();
			role2.setId(AppConstants.ROLE_NORMAL);
			role2.setName("ROLE_NORMAL");

			List<Role> role = List.of(role1,role2);
			this.roleRepo.saveAll(role);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
