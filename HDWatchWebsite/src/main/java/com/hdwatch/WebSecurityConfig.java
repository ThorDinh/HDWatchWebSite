package com.hdwatch;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hdwatch.entity.Accounts;
import com.hdwatch.service.AccountsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	AccountsService accountService;
	
	@Autowired
	HttpSession session;
	
	// Cung cấp nguồn dữ liệu đăng nhập
	
	
	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.csrf().disable();
		http.authorizeRequests()
//			.antMatchers("/order/**").authenticated()
//			.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
//			.antMatchers("/rest/authorities").hasRole("DIRE")
			.anyRequest().permitAll();
		
		http.formLogin();
		

		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
			.accessDeniedPage("/security/unauthoried");
		
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff");		
		
		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
	}
	
	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Cho phép truy xuất REST API từ domain khác
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
	
}
