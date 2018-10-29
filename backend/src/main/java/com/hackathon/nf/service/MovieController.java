package com.hackathon.nf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @Autowired
    RestTemplate restTemplate;

    @Value("${tmdb.token}")
    String token;
    
    @Value("${tmdb.endpoint}")
    String endpoint;

    @RequestMapping("/genres")
    public String genres() {
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint + "/genre/movie/list")
                .queryParam("api_key", token);
        
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
    
    @RequestMapping("/search")
    public String search(@RequestParam("with_genres") String genres, 
            @RequestParam("with_keywords") String keywords,
            @RequestParam("sort_by") String sort) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint + "/discover/movie")
                .queryParam("api_key", token)
                .queryParam("with_genres", genres)
                .queryParam("with_keywords", keywords)
                .queryParam("sort_by", sort);
        
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

}
