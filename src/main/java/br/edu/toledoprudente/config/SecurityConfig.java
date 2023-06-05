package br.edu.toledoprudente.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	


	@Autowired
	DataSource dataSource;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests((requests) -> requests

				.requestMatchers("/login").permitAll()
				.requestMatchers("/cadastro").permitAll()
				.requestMatchers("/cliente/cadastrar").permitAll()
				.requestMatchers("/principal").permitAll()
				
				.requestMatchers("/funcionario/novo/**").hasAuthority("ADM")
				.requestMatchers("/funcionario/listar/**").hasAuthority("ADM")
				.requestMatchers("/cliente/novo/**").hasAuthority("ADM")
				.requestMatchers("/cliente/listar/**").hasAuthority("ADM")
				.requestMatchers("/produto/novo/**").hasAuthority("ADM")
				.requestMatchers("/produto/listar/**").hasAuthority("ADM")
				.requestMatchers("/categoria/novo/**").hasAuthority("ADM")
				.requestMatchers("/categoria/listar/**").hasAuthority("ADM")
				.requestMatchers("/fornecedor/novo/**").hasAuthority("ADM")
				.requestMatchers("/fornecedor/listar/**").hasAuthority("ADM")
				.requestMatchers("/venda/novo/**").hasAuthority("ADM")
				.requestMatchers("/venda/listardetalhe/**").hasAuthority("ADM")
				
				
				
				
				
				.requestMatchers("/funcionario/novo/**").hasRole("ADM")
				.requestMatchers("/funcionario/listar/**").hasRole("ADM")
				.requestMatchers("/cliente/novo/**").hasRole("ADM")
				.requestMatchers("/cliente/listar/**").hasRole("ADM")
				.requestMatchers("/produto/novo/**").hasRole("ADM")
				.requestMatchers("/produto/listar/**").hasRole("ADM")
				.requestMatchers("/categoria/novo/**").hasRole("ADM")
				.requestMatchers("/categoria/listar/**").hasRole("ADM")
				.requestMatchers("/fornecedor/novo/**").hasRole("ADM")
				.requestMatchers("/fornecedor/listar/**").hasRole("ADM")
				.requestMatchers("/venda/novo/**").hasRole("ADM")
				.requestMatchers("/venda/listardetalhe/**").hasRole("ADM")
				
				.anyRequest().authenticated()
				
				
				
		).formLogin(
				(form) -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll().failureUrl("/login-error"))
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
						.permitAll());

		return http.build();
	}
	

	

}