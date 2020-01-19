/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cocktail.cocktails;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Brecht
 */
@RestController
public class CocktailResource {
    @Autowired
    private CocktailRepository repository;
    
    @Value("${COCKTAIL_INGREDIENT_URI:http://localhost:8080}")
    private String COCKTAIL_INGREDIENT_URI;
    
    @Value("${COCKTAIL_TAG_URI:http://localhost:5000}")
    private String COCKTAIL_TAG_URI;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
    
    @RequestMapping(value="/cocktail", method=RequestMethod.GET)
    @ResponseBody
    public List<Cocktail> getAllCocktails(){
        return repository.findAll();
    }
    
    @RequestMapping(value="/cocktail/{id}", method=RequestMethod.GET)
    public @ResponseBody Cocktail getCocktailById(@PathVariable("id") long id){
        return repository.findById(id);
    }
    
    @RequestMapping(value="/cocktail/id", method=RequestMethod.POST)
    public @ResponseBody List<Cocktail> getCocktailsByIds(@RequestBody List<Long> ids){
        return repository.findByIdIn(ids);
    }
    
    @RequestMapping(value="/cocktail/ingredient/{id}", method=RequestMethod.GET)
    public @ResponseBody List<Cocktail> getCocktailsByIngredientId(@PathVariable("id") long id){
        final String uri = COCKTAIL_INGREDIENT_URI + "/cocktail/" + String.valueOf(id);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long[]> response = restTemplate.getForEntity(uri, Long[].class);
        Long[] cocktailIds = response.getBody();
        if(cocktailIds.length == 0) return null;
        
        return repository.findByIdIn(Arrays.asList(cocktailIds));
    }
    
    @RequestMapping(value="/cocktail", method=RequestMethod.POST)
    public @ResponseBody long saveCacktail(@RequestBody Cocktail cocktail){
        return repository.save(cocktail).getId();
    }
    
    @RequestMapping(value="/cocktail/{id}", method=RequestMethod.DELETE)
    public void deleteCocktail(@PathVariable("id") long id){
        repository.deleteById(id);
    }
}
