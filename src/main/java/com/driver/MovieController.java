package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    //adding movies
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String st= movieService.addMovie(movie);
        return new ResponseEntity<>(st, HttpStatus.CREATED);
    }
    //adding director
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String st= movieService.addDirector(director);
        return new ResponseEntity<>(st, HttpStatus.CREATED);
    }
    //adding pair movie-director
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName,@RequestParam("director") String directorName){
        String st=movieService.add_MovieDirectorPair(movieName,directorName);
        if(st==null){
            return new ResponseEntity<>("Invalid",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(st,HttpStatus.CREATED);
    }

    //get movie by movie name
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie m=movieService.getMovieByName(movieName);
        if(m==null){
            return new ResponseEntity("Invalid movie name",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(m,HttpStatus.FOUND);
    }

    //5-get director name by director
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director d=movieService.getDirectorByName(directorName);
        if(d==null){
            return new ResponseEntity("Invalid director name",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(d,HttpStatus.FOUND);
    }

    //6-Get List of movies name for a given director name: GET
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> list=movieService.getMoviesByDirectorName(directorName);
        if(list==null){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    //7-Get List of all movies added: GET
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List lst=movieService.findAllMovies();
        return new ResponseEntity<>(lst,HttpStatus.FOUND);
    }
    //8-Delete a director and its movies from the records: DELETE
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String directorName){
        String s= movieService.deleteDirectorByName(directorName);
        if(s==null){
            return new ResponseEntity<>("Invalid director name",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(s,HttpStatus.FOUND);

    }

    //9-Delete all directors and all movies by them from the records: DELETE
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String s=movieService.deleteAllDirectors();
        return new ResponseEntity<>(s,HttpStatus.FOUND);
    }


}
