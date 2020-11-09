package com.example.authenticatingldap;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "Welcome to the home page!";
	}
	
	@GetMapping("/for-user-role")
	@RolesAllowed(value = "ROLE_USER")
	public String roleBased()
	{
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return " Ben Can view Only this page";
	}
	@GetMapping("/for-user-role-joe")
	
	@RolesAllowed(value = "ROLE_JOE")
	public String roleBased2()
	{System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "Only JOE Can view this page";
	}
	
	@GetMapping("/for-user-role-bob")
	
	@Secured("ROLE_BOB")
	public String roleBased3()
	{System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "Only BOB Can view this page";
	}
}
