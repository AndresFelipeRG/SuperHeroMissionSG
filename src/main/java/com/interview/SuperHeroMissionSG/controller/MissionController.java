package com.interview.SuperHeroMissionSG.controller;

import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.model.SuperHero;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
import com.interview.SuperHeroMissionSG.repository.SuperHeroRepository;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MissionController {
    
    @Autowired
    MissionRepository missionRepository;
    
    @Autowired
    SuperHeroRepository superHeroRepository;
    
    @RequestMapping(value="/createMission", method = RequestMethod.POST)
    public ResponseEntity<String> createMission(@RequestBody Map<String, String> parameters){
        if(parameters.get("missionName") == null ||parameters.get("missionName").isEmpty() ){
            return new ResponseEntity<>("{\"emptyMissionName\": true}", HttpStatus.OK);
        }
        if(parameters.get("superHeroName") == null ||parameters.get("superHeroName").isEmpty() ){
            return new ResponseEntity<>("{\"superHeroNameEmpty\": true}", HttpStatus.OK);
        }
        if(parameters.get("isDeleted").equals("true")){
            return new ResponseEntity<>("{\"missionAlreadyDeleted\": true}", HttpStatus.OK);
        }
        List<SuperHero> heroes = superHeroRepository.findBySuperHeroName(parameters.get("superHeroName"));
        if(!findMissionDuplicates(parameters.get("missionName") )){
            if(heroes.size() > 0){
                saveMission(parameters);
                notifySuperHeroMissionCreated(parameters);
            }
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else{
            List<SuperHero> heroesByMissionName = superHeroRepository.findByMissionName(parameters.get("missionName"));
            if(!heroesByMissionName.removeIf(hero -> hero.getSuperHeroName().equals(parameters.get("superHeroName")))){
                if(heroes.size() > 0){
                    saveMission(parameters);
                }
            }
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }
    @RequestMapping(value="/addSuperHeroToMission", method = RequestMethod.POST)
    public ResponseEntity<String> addSuperHero(@RequestBody Map<String, String> parameters)
    { 
        return new ResponseEntity<>("", HttpStatus.OK);
    }
   
    @RequestMapping(value="/getAllMissions", method= RequestMethod.GET)
    public ResponseEntity<String> getAllMissions() throws JSONException{    
        JSONArray missions= new JSONArray();
        for(Mission mission: missionRepository.findAll()){
            getMission(missions, mission);
        }
        return new ResponseEntity<>(missions.toString(), HttpStatus.OK); 
    }
    @RequestMapping(value="/getMissionByName", method= RequestMethod.GET)
    public ResponseEntity<String> getMissionByName(@RequestParam Map<String, String> parameters) throws JSONException{ 
        JSONArray missions= new JSONArray();
        for(Mission mission: missionRepository.findByMissionName(parameters.get("missionName"))){
            getMission(missions, mission);
        }
        return new ResponseEntity<>(missions.toString(), HttpStatus.OK);  
    }
    @RequestMapping(value="/getMissionBySuperHeroName", method= RequestMethod.GET)
    public ResponseEntity<String> getMissionBySuperHeroName(@RequestParam Map<String, String> parameters) throws JSONException{   
        JSONArray missions= new JSONArray();
        for(Mission mission: missionRepository.findBySuperHeroName(parameters.get("superHeroName"))){
            getMission(missions, mission);
        }
        return new ResponseEntity<>(missions.toString(), HttpStatus.OK);
        
    }
    @RequestMapping(value = "/updateMission", method =RequestMethod.POST)
    public ResponseEntity<String> udpdateMission(@RequestBody Map<String, String> parameters){
        if(parameters.get("missionName") == null ||parameters.get("missionName").isEmpty() ){
            return new ResponseEntity<>("{\"emptyMissionName\": true}", HttpStatus.OK);
        }
        if(parameters.get("_missionName") == null ||parameters.get("_missionName").isEmpty() ){
            return new ResponseEntity<>("{\"emptyNewMissionName\": true}", HttpStatus.OK);
        }
        if(parameters.get("_isCompleted") == null){
            return new ResponseEntity<>("{\"empty_isCompleted\": true}", HttpStatus.OK);
        }
        if(parameters.get("_isDeleted") == null){
            return new ResponseEntity<>("{\"empty_isDeleted\": true}", HttpStatus.OK);
        }
        if(!findMissionDuplicates(parameters.get("_missionName")) ||
                                                (parameters.get("isCompleted") != (parameters.get("_isCompleted"))
                                                 && parameters.get("missionName").equals(parameters.get("_missionName")))
                                                ){
            List<Mission> missions = missionRepository.findByMissionName(parameters.get("missionName"));
            for(Mission mission: missions){
                mission.setCompleted((parameters.get("_isCompleted")).equals("true"));
                mission.setDeleted((parameters.get("_isDeleted")).equals("true"));
                mission.setMissionName(parameters.get("_missionName"));
                missionRepository.save(mission);
            } 
            notifySuperHeroesNameChange(parameters);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @RequestMapping(value="/deleteMission", method=RequestMethod.POST)
    public ResponseEntity<String> deleteMission(@RequestBody Map<String, String> parameters){
        if(parameters.get("missionName") == null ||parameters.get("missionName").isEmpty() ){
            return new ResponseEntity<>("{\"emptyMissionName\": true}", HttpStatus.OK);
        }
        List<Mission> missions = missionRepository.findByMissionName(parameters.get("missionName"));
        for(Mission mission: missions){
            missionRepository.delete(mission);
        }
        List<SuperHero> heroes = superHeroRepository.findByMissionName(parameters.get("missionName"));
        for(SuperHero hero: heroes){
            superHeroRepository.delete(hero);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    private void getMission(JSONArray missions, Mission mission) {
        JSONObject missionJson = new JSONObject();
        missionJson.put("missionName", mission.getMissionName());
        missionJson.put("superHeroName", mission.getSuperHeroName());
        missionJson.put("isCompleted", mission.isCompleted());
        missionJson.put("isDeleted", mission.isDeleted());
        missions.put(missionJson);
    }
    private boolean findMissionDuplicates(String missionName) {
        List<Mission> duplicates = missionRepository.findByMissionName(missionName);
        return  duplicates.size() > 0;
    }
    private void notifySuperHeroMissionCreated(Map<String, String> parameters) {
        List<SuperHero> heroes = superHeroRepository.findBySuperHeroName(parameters.get("superHeroName"));
        if(heroes.size() > 0){
            superHeroRepository.save(SuperHero.builder().firstName(heroes.get(0).getFirstName())
                                                    .lastName(heroes.get(0).getLastName())
                                                    .missionName(parameters.get("missionName"))
                                                    .superHeroName(heroes.get(0).getSuperHeroName())
                                                    .build());}
    }
    private void notifySuperHeroesNameChange(Map<String, String> parameters){
        List<SuperHero> heroes = superHeroRepository.findByMissionName(parameters.get("missionName"));
        for(SuperHero hero: heroes){
            hero.setMissionName(parameters.get("_missionName"));
            superHeroRepository.save(hero);
        }
    }
    private void saveMission(Map<String, String> parameters) {
        missionRepository.save(Mission.builder().isCompleted( (parameters.get("isCompleted")) == null?false:(parameters.get("isCompleted")).equals("true"))
                                                .isDeleted(parameters.get("isDeleted") == null?false:parameters.get("isDeleted").equals("true"))
                                                .missionName((String) (parameters.get("missionName") ))
                                                .superHeroName((String) parameters.get("superHeroName")== null ? "": parameters.get("superHeroName"))
                                                .build());
   }
}
