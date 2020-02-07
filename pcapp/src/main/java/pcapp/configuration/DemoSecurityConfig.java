package pcapp.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pcapp.services.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// reference to our security data source
    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	/*
	 // reference to security data source (when JDBC authentication)
	 	
	@Autowired
	private DataSource securityDataSource;
	*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		// A) users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("rene").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mari").password("test123").roles("MANAGER", "EMPLOYEE"))
			.withUser(users.username("oto").password("test123").roles("ADMIN", "EMPLOYEE"));
			
				
		// B) using jdbc authentication
		
		//auth.jdbcAuthentication().dataSource(securityDataSource);
		 */	
		
		// C) custom user authentication
		
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        
		http.authorizeRequests()
			.antMatchers("/").permitAll() // public access to home page
			.antMatchers("/app/**").hasRole("APPLICANT")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/manag/**").hasRole("MANAGER")
		.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/authUser")
			.successHandler(customAuthenticationSuccessHandler)
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/")
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/accessDenied");

	}
	
	// Beans
	// bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	auth.setUserDetailsService(userService);
	auth.setPasswordEncoder(passwordEncoder());
	return auth;
	}
		
}






