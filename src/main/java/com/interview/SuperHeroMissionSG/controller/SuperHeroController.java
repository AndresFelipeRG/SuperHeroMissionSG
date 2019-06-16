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
}
