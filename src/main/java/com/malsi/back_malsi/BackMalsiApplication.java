package com.malsi.back_malsi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.malsi.back_malsi.model.Category;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.CategoryRepository;
import com.malsi.back_malsi.repository.UserRepository;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class BackMalsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackMalsiApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository) {
		return args -> {
			String email = "admin@admin.com";

			if (!userRepository.findByEmail(email).isPresent()) {
				User user = new User();
				user.setName("Admin");
				user.setEmail(email);
				user.setAddress("Paris");
				user.setPhone("06060606060606");
					user.setPassword(passwordEncoder.encode("admin123"));
				user.setRole("ADMIN");

				userRepository.save(user);
				System.out.println("Utilisateur admin créé avec succès.");
			} else {
				System.out.println("L'utilisateur admin existe déjà.");
			}

			if (!categoryRepository.findByLabel("service").isPresent()) {
				Category category = new Category();
				category.setLabel("service");
				category.setPriority("moyenne");

				categoryRepository.save(category);
				System.out.println("Catégorie service créée avec succès.");
			}

			if (!categoryRepository.findByLabel("operation").isPresent()) {
				Category category = new Category();
				category.setLabel("operation");
				category.setPriority("haute");

				categoryRepository.save(category);
				System.out.println("Catégorie operation créée avec succès.");
			}
		};
	}
}
