package com.bitbuy.userregistration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bitbuy.userregistration.filter.ExceptionHandlerFilter;
import com.bitbuy.userregistration.filter.JwtRequestFilter;

@Configuration
@Lazy
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private ExceptionHandlerFilter exceptionHandlerFilter;
	
	@Autowired
    DataSource dataSource;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    
        //default schema to create users
        auth.jdbcAuthentication()
        .dataSource(dataSource).usersByUsernameQuery("select USERNAME,PASSWORD, ENABLED from USER_ACCOUNT where username = ?");        
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers(
        		"/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/registration/**",
                "/h2-console/**",
                "/error"
                );
    }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	
		httpSecurity.csrf().disable()
				.authorizeRequests().
				antMatchers("/login").permitAll().
				anyRequest().authenticated().and().
				exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);
	}
}
