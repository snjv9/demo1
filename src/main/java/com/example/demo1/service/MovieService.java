package com.example.demo1.service;

import com.example.demo1.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {
    public Movie saveNewMovie(Movie movie) ;

    public List<Movie> getAllMovie();

    public Optional<Movie> getById(int id) ;

    public boolean delete(int id) ;

    public Movie updateById(Movie movie, int id) ;
}
