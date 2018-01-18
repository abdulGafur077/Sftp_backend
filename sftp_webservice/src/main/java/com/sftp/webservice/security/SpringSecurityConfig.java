package com.sftp.webservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	private final UserDetailService				userService;
	private final TokenAuthenticationService	tokenAuthenticationService;
	private String								authorizationExclusion[]	= new String[]
	{
		"/",
		"**/*.html",
		"**/*.css",
		"**/*.js"
	};

	public SpringSecurityConfig()
	{
		super(true);
		this.userService = new UserDetailService();
		tokenAuthenticationService = new TokenAuthenticationService(userService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// super.configure(http);
		http.exceptionHandling().and().servletApi().and().headers().cacheControl();
		http.headers().cacheControl().disable();
		http.authorizeRequests().antMatchers(authorizationExclusion).permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailService userDetailsService()
	{
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService()
	{
		return tokenAuthenticationService;
	}

}
