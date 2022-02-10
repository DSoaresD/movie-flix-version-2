package com.devsuperior.movieflix.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	// obtem o nome do usuario que ja foi reconhecido pelo spring security(no caso o email)
	@Transactional(readOnly = true) //Lembrando que o transactional serve para que esta operação nao faça o lock no banco de dados
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		}catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
}