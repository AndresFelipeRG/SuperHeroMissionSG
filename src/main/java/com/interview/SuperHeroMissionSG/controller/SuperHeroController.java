package com.interview.SuperHeroMissionSG.controller;

import com.interview.SuperHeroMissionSG.model.SuperHero;
import com.interview.SuperHeroMissionSG.repository.SuperHeroRepository;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperHeroController {
    @Autowired
    private SuperHeroRepository superHeroRepository;
    
    @RequestMapping(value="/createSuperHero", method = RequestMethod.POST)
    public ResponseEntity<String> createSuperHero(@RequestParam Map<String, String> parameters){
        superHeroRepository.save(SuperHero.builder().firstName(parameters.get("firstName"))
                                                    .lastName(parameters.get("lastName"))
                                                    .missionName(parameters.get("missionName"))
                                                    .superHeroName(parameters.get("superHeroName"))
                                                    .build()
                                               );
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(value="/getAllSuperHeroes", method= RequestMethod.GET)
    public ResponseEntity<String> getAllSuperHeroes() throws JSONException{
        
        JSONArray heroes = new JSONArray();
        for(SuperHero hero: superHeroRepository.findAll()){
            getSuperHero(heroes, hero);
        }
        return new ResponseEntity<>(heroes.toString(), HttpStatus.OK);
        
    }
    @RequestMapping(value="/getSuperHeroByName", method= RequestMethod.GET)
    public ResponseEntity<String> getSuperHeroByName(@RequestParam Map<String, String> parameters) throws JSONException{
        
        JSONArray heroes= new JSONArray();
        for(SuperHero hero: superHeroRepository.findBySuperHeroName(parameters.get("superHeroName"))){
            getSuperHero(heroes, hero);
        }
        return new ResponseEntity<>(heroes.toString(), HttpStatus.OK);
        
    }
    @RequestMapping(value="/getSuperHeroByMissionName", method= RequestMethod.GET)
    public ResponseEntity<String> getSuperHeroByMissionName(@RequestParam Map<String, String> parameters) throws JSONException{
        
        JSONArray heroes= new JSONArray();
        for(SuperHero hero: superHeroRepository.findByMissionName(parameters.get("missionName"))){
            getSuperHero(heroes, hero);
        }
        return new ResponseEntity<>(heroes.toString(), HttpStatus.OK);
        
    }
    private void getSuperHero(JSONArray heroes, SuperHero hero) {
        JSONObject heroJson = new JSONObject();
        heroJson.put("superHeroName", hero.getSuperHeroName());
        heroJson.put("missionName", hero.getSuperHeroName());
        heroJson.put("firstName", hero.getFirstName());
        heroJson.put("lastName", hero.getLastName());
        heroes.put(heroJson);
    }
}
