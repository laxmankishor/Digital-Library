package com.example.Digital_Library_Management_System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Digital_Library_Management_System.model.Admin;
import com.example.Digital_Library_Management_System.model.User;
import com.example.Digital_Library_Management_System.service.AdminService;

@SpringBootApplication
public class DigitalLibraryApplication implements CommandLineRunner {

		// @Autowired
		// AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(DigitalLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 		Admin admin = Admin.builder()
		// 		.name("Laxman Kishor")
		// 		.email("laxmankishor@gmail.com")
		// 		.user(User.builder()
		// 				.userName("laxmankishor")
		// 				.password("123456789")
		// 				.build())
		// 		.build();
		// adminService.createAdmin(admin);
	}

}
