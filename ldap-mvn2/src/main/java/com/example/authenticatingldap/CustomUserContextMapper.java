package com.example.authenticatingldap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.directory.Attributes;

import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
@Configuration
public class CustomUserContextMapper extends LdapUserDetailsMapper {

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) 
	{

	    Attributes attributes = ctx.getAttributes();
	    Object[] groups = new Object[100];
	    groups = ctx.getObjectAttributes("uid");
	    System.out.println("Attributes: {}"+attributes);
	   // LOGGER.debug("Attributes: {}", attributes);

	    Set<GrantedAuthority> authority = new HashSet<GrantedAuthority>();

	    for(Object group: groups)
	    {

	        if (group.toString().toLowerCase().contains("dhcpdelhi".toLowerCase()) == true)
	        {
	            authority.add(new SimpleGrantedAuthority("ROLE_USER"));
	            break;          
	        }
	    }
	    
	    User userDetails = new User(username, "", false, false, false, false, authority);
	    return userDetails;
	}
}
