package com.hackathon.nf.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hackathon.nf.model.FoodAlcoholPair;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

	List<FoodAlcoholPair> pairings = new ArrayList<>();
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${thecocktaildb.endpoint}")
	String baseUrl;

	@RequestMapping(path = "/cocktail/search/{cocktail-name}")
	public String getCocktailSearch(@PathVariable("cocktail-name") String cocktailName) {
		HttpHeaders headers = new HttpHeaders();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search.php")
                .queryParam("s", cocktailName);
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody(); 
	}
	
	@RequestMapping(path = "/cocktail/search/alcohol/{alcohol}")
	public String getByAlcohol(@PathVariable("alcohol") String alcohol) {
		HttpHeaders headers = new HttpHeaders();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/filter.php")
                .queryParam("i", alcohol);
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody(); 
	}
	
	@RequestMapping(path = "/cocktail/search/id/{id}")
	public String getById(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/lookup.php")
				.queryParam("i", id);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	@RequestMapping(path = "/pairings")
	@ResponseStatus(HttpStatus.OK)
	public Collection<FoodAlcoholPair> getPairings() {
		List<FoodAlcoholPair> fap = new ArrayList<>();
		
		fap.add(new FoodAlcoholPair("Italian", "Amaretto"));
		fap.add(new FoodAlcoholPair("Mexican", "Tequila"));
		fap.add(new FoodAlcoholPair("Mexican", "Brandy"));
		fap.add(new FoodAlcoholPair("American", "Whiskey"));
		fap.add(new FoodAlcoholPair("American", "Bourbon"));
		
		return fap; 
	}

}
