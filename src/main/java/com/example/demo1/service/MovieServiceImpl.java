package com.example.demo1.service;

import com.example.demo1.domain.Movie;
import com.example.demo1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){this.movieRepository = movieRepository;}

    @Override
    public Movie saveNewMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovie() {
      return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getById(int id) {
        Optional<Movie> movieId = movieRepository.findById(id);
        return movieId;
    }

    @Override
    public boolean delete(int id) {
        Optional<Movie> movieId = movieRepository.findById(id);
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public Movie updateById(Movie movie, int id) {
        Optional<Movie> userOptional = movieRepository.findById(id);
        movie.setId(id);
        movieRepository.save(movie);
        return movie;
    }


}
