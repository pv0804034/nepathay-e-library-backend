package com.nepathya.archive.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.security.filter.JWTAuthorizationFilter;
import com.nepathya.archive.system.security.user.UserService;


@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${jwt.secret}")
	private String tokenSecret;

	@Value("${jwt.header}")
	private String header;

	@Value("${jwt.prefix}")
	private String prefix;

	@Autowired
	private UserService userService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public Pbkdf2PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder(tokenSecret);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().httpBasic().disable();
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated().and().addFilterBefore(
				new JWTAuthorizationFilter(authenticationManager(), userService, header, prefix, tokenSecret),
				BasicAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs/**");
		web.ignoring().antMatchers("/swagger.json");
		web.ignoring().antMatchers("/swagger-ui.html");
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/swagger-resources/**");
		web.ignoring().antMatchers("/swagger-resources/**");
		web.ignoring().antMatchers("/" + UrlConstant.LOGIN);
		web.ignoring().antMatchers("/" + UrlConstant.USER + "/**");
		web.ignoring().antMatchers("/" + UrlConstant.FACULTY + "/**");
		web.ignoring().antMatchers(HttpMethod.POST,"/" + UrlConstant.USER + "/**");	
		web.ignoring().antMatchers(HttpMethod.GET,"/" + UrlConstant.DOCUMENT + "/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/" + UrlConstant.FILE_UPLOAD_SINGLE + "/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/" + UrlConstant.MESSAGE + "/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/" + UrlConstant.TAG + "/**");
//		web.ignoring().antMatchers("/" + SecurityUrlConstant.VERIFY_REGISTRATION);
//		web.ignoring().antMatchers("/" + SecurityUrlConstant.REQUEST_PASSWORD_RESET);
//		web.ignoring().antMatchers("/" + SecurityUrlConstant.RESET_PASSWORD);
//		web.ignoring().antMatchers("/api/v1/supplies/**");
//		web.ignoring().antMatchers("/api/test/**");
	
	}
}
