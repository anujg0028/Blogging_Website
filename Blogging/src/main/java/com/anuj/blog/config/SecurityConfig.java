package com.anuj.blog.config;
/*

//[Old deprecated way of using securityConfig by implementing it to WebSecurityConfigurerAdapter]

import com.anuj.blog.security.JWTAuthenticationEntryPoint;
import com.anuj.blog.security.JWTAuthenticationFilter;
import com.anuj.blog.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {   //using WebSecurityConfigAdapter is older so we are using new

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	//without JWT Token
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//	}

	public static final String[] all_Public_URL = {"/api/v1/auth/**",
			"/v3/api-docs",   // provide swagger doc
			"/v2/api-docs",   //uses swagger doc i.e our access point to use swagger
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf()
				.disable()
				.authorizeHttpRequests()
		//		.antMatchers("/api/v1/auth/login").permitAll()   //used to make specific url public  //We can add as many url as we want to make them public
		//		.antMatchers("/api/v1/auth/**").permitAll()   //used to allow all apis that start with following route
		//		.antMatchers(HttpMethod.GET).permitAll()   //allow all get apis
		//		.antMatchers("/v3/api-docs").permitAll()
				.antMatchers(all_Public_URL).permitAll()   //used when we dont want to add seperate antMatcher url
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		//http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// super.configure(auth);
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

 */
