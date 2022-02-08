package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatorio")
	private String name;
	
	@Email(message = "Favor entrar com email valido")
	private String email;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	
	public UserDTO() {		
	}

	public UserDTO(Long id, String name, String email) {

		this.id = id;
		this.name = name;
		
		this.email = email;
		
	}
	
	public UserDTO(User entity) {
	
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		entity.getRoles().forEach(x -> this.roles.add(new RoleDTO(x)));
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setFirstName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}
	
	
}
