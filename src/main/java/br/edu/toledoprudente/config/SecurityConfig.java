package br.edu.toledoprudente.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests((requests) -> requests

				.requestMatchers("/login").permitAll()
				// .requestMatchers("/usuario/novo/**").hasRole("ADM")
				.requestMatchers("/cadastro").permitAll()
				.requestMatchers("/cliente/cadastrar").permitAll()
				.anyRequest().authenticated()
				
				
				
		).formLogin(
				(form) -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll().failureUrl("/login-error"))
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
						.permitAll());

		return http.build();
	}

	/* Cria usuário no banco de dados */
	/*
	 * @Bean UserDetailsManager users(DataSource dataSource) {
	 * 
	 * UserDetails user = User.builder() .username("anapaula@ana.com")
	 * .password(passwordEncoder().encode("password")) .roles("ADM") .build();
	 * 
	 * JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
	 * users.createUser(user);
	 * 
	 * return users; }
	 * 
	 * 
	 * //Carrega os usuários do banco /* @Bean public UserDetailsManager
	 * users(DataSource dataSource) { JdbcUserDetailsManager users = new
	 * JdbcUserDetailsManager(dataSource); return users; }
	 * 
	 * //Define o Encriptador padrão
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(16); }
	 */

}