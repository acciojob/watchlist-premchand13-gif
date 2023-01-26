package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> movieDB=new HashMap<>();
    Map<String ,Director> directorDB=new HashMap<>();
    Map<String, List<String>> pairDB=new HashMap<>();
    //1 addMovie
    public String addMovie(Movie movie){
        movieDB.put(movie.getName(),movie);
        return "success";
    }
    //2-addDirector
    public String addDirector(Director director){
        directorDB.put(director.getName(),director);
        return "success";
    }
    //3-add pair
    public String add_MovieDirectorPair(String mName,String dName){
        if(!movieDB.containsKey(mName)||!directorDB.containsKey(dName)){
            return null;
        }

        if(pairDB.containsKey(dName)){
            List<String> lst= pairDB.get(dName);
           lst.add(mName);
           pairDB.put(dName,lst);
        }
        else{
            List<String> lst=new ArrayList<>();
            lst.add(mName);
            pairDB.put(dName,lst);
        }
//        String s=pairDB.get(dName).get(0);
//        return ""+pairDB.get(dName).size();
        return "success";
    }

    //4-get movie object
    public Movie getMovieByName(String mName){
        if(!movieDB.containsKey(mName)){
            return null;
        }
        return movieDB.get(mName);
    }
    //5-get director object
    public Director getDirectorByName(String dName){
        if(!directorDB.containsKey(dName)){
            return null;
        }
        return directorDB.get(dName);
    }

    //6-list of movies by director
    public List<String> getMoviesByDirectorName(String dName){
        if(pairDB.containsKey(dName)){
            return pairDB.get(dName);
        }
        return null;
    }
    //7-list of all movies
    public List<String> findAllMovies(){
        List<String> lst = new ArrayList<>(movieDB.keySet());
        return lst;
    }
    //8-delete director-movies
    public String deleteDirectorByName(String dName){
        if(!pairDB.containsKey(dName)){
            return null;
        }
        List<String> lst=pairDB.get(dName);
        pairDB.remove(dName);
        for(String s:lst){
            movieDB.remove(s);
        }
        directorDB.remove(dName);
        return "success";
    }

    //9-delete all director-movies
    public String deleteAllDirectors(){
        for(String d:directorDB.keySet()){
            if(pairDB.containsKey(d)){
                List<String> lst=pairDB.get(d);
                pairDB.remove(d);
                for(String s:lst){
                    movieDB.remove(s);
                }

            }
        }
        directorDB.clear();
        return "success";
    }

}
