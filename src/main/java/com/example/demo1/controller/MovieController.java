package com.example.demo1.controller;

import com.example.demo1.domain.Movie;
import com.example.demo1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:4200")    //Cross origin Resource sharing (CORS) for allowing only a
@RequestMapping("api/v1")                          //particular domain to do (CRUD) operations
public class MovieController {
    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PutMapping("/saveNewMovie")
    public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie) {
        try {
            movieService.saveNewMovie(movie);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Movie>(movie, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getAllMovies")
    public ResponseEntity<?> getAllMovies() {
        try {
            return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovie(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("No movies found",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Optional<Movie> movies;
        try{
            movies = movieService.getById(id);
            movieService.getById(id);
            return new ResponseEntity<Optional<Movie>>(movies,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>("No such movie found",HttpStatus.CONFLICT);
        }
    }



    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            movieService.delete(id);
            return new ResponseEntity<String >("Movie deleted.",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Cannot be deleted because movie doesn't exist",HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@RequestBody Movie movie, @PathVariable int id){
        try {
            movieService.updateById(movie, id);
            return new ResponseEntity<>(movieService.updateById(movie,id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }
}