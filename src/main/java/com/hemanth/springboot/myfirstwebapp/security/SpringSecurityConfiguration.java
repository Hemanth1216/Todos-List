package com.hemanth.springboot.myfirstwebapp.security;
import static org.springframework.security.config.Customizer.withDefaults;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
	
			
		UserDetails userdetails1 = addNewUser("Hemanth","1216");
		UserDetails userdetails2 = addNewUser("Chanakya","chanu");
		UserDetails userdetails3 = addNewUser("Srinu","nandini");
		
		
		return new InMemoryUserDetailsManager(userdetails1,userdetails2,userdetails3);
	}

	private UserDetails addNewUser(String username, String password) {
		Function<String, String> passwordEncoder 
		= input -> passwordEncoder().encode(input);
		
		UserDetails userdetails = User.builder()
								.passwordEncoder(passwordEncoder)
								.username(username)
								.password(password)
								.roles("USER","ADMIN")
								.build();
		return userdetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(     //authorize all type of requests
				auth -> auth.anyRequest().authenticated());
		
		http.formLogin(withDefaults()); //for unauthorized requests shoe login form page
		
		http.csrf().disable();			//disable CSRF
		
		http.headers().frameOptions().disable(); //disable FrameOptions
		
		return http.build();
		
	}
}
