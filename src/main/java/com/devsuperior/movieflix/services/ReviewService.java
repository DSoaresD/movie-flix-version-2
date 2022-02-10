package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService  {
	
	@Autowired
	private ReviewRepository repository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
	Review entity = new Review();
	entity.setText(dto.getText());
	User user = new User();
	user.setId(dto.getUserId());	
	Movie movie = new Movie();
	movie.setId(dto.getMovieId());
	entity.setMovie(movie);
	entity = repository.save(entity);
	return new ReviewDTO(entity);
	
	}

}
