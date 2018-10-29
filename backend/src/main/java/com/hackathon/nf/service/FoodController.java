package com.hackathon.nf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/businesses")
public class FoodController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${yelp.token}")
    String token;
    
    @Value("${yelp.endpoint}")
    String yelpEndpoint;

    @RequestMapping("/search")
    public String search(@RequestParam("location") String location, 
            @RequestParam("categories") String categories) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(yelpEndpoint + "/businesses/search")
                .queryParam("location", location)
                .queryParam("categories", categories);
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
    
    @RequestMapping("/{id}")
    public String details(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(yelpEndpoint + "/businesses/" + id);
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
