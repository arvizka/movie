package com.xsis.movie.service;

import com.xsis.movie.model.Movie;
import com.xsis.movie.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new EntityNotFoundException("Movie with id : ".concat(id.toString()).concat(" is not found."));
        }
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        Optional<Movie> oldMovie = movieRepository.findById(movie.getId());
        if (oldMovie.isEmpty()) {
            return movieRepository.save(movie);
        } else {
            throw new DuplicateKeyException("Movie with id : ".concat(movie.getId().toString()).concat(" is already exists."));
        }
    }

    public Movie update(Long id, Movie movie) {
        Optional<Movie> oldMovie = movieRepository.findById(id);
        if (oldMovie.isPresent()) {
            return movieRepository.save(movie);
        } else {
            throw new DuplicateKeyException("Movie with id : ".concat(movie.getId().toString()).concat(" is not found."));
        }
    }

    public void delete(Long id) {
        Optional<Movie> oldMovie = movieRepository.findById(id);
        if (oldMovie.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new DuplicateKeyException("Movie with id : ".concat(id.toString()).concat(" is not found."));
        }

    }


}
