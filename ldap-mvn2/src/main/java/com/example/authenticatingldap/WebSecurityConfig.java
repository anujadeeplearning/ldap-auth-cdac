package com.example.authenticatingldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserContextMapper customUserContextMapper;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication().userSearchBase("dc=cdac,dc=in")
				.userDnPatterns("uid={0},ou=Systems")
			//	.groupSearchBase("ou=groups")
				.userDetailsContextMapper(customUserContextMapper)
				.contextSource()
					.url("ldap://10.224.0.21:389/dc=cdac,dc=in")
					.managerDn("uid=dhcpdelhi,ou=Systems,dc=cdac,dc=in")
					.managerPassword("dl@123unified")
					.port(389);
					
					//.and()
				//.passwordCompare()
					//.passwordEncoder(new BCryptPasswordEncoder())
					//.passwordAttribute("userPassword");
	}

	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
        .ldapAuthentication()
        .userDetailsContextMapper(customUserContextMapper)
        .userSearchFilter("(uid={0})")
        .userSearchBase("dc=zflexsoftware,dc=com")
        .groupSearchFilter("uniqueMember={0}")
        .groupSearchBase("ou=sysadmins,dc=zflexsoftware,dc=com")
        .userDnPatterns("uid={0}")
        .contextSource()
        .url("ldap://www.zflexldap.com:389")
        .managerDn("cn=ro_admin,ou=sysadmins,dc=zflexsoftware,dc=com")
        .managerPassword("zflexpass");
	}
*/
}
