package com.hackathon.nf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${thecocktaildb.endpoint}")
	String baseUrl;

	@RequestMapping(path = "/cocktail/search/{cocktail-name}")
	public String getCocktailSearch(@PathVariable("cocktail-name") String cocktailName) {
		HttpHeaders headers = new HttpHeaders();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search.php?s=")
                .queryParam("cocktail", cocktailName);
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody(); 
	}

}
