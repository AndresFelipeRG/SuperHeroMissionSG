package com.interview.SuperHeroMissionSG.controller;

import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
import java.util.List;
import java.util.Map;
import org.assertj.core.util.Preconditions;
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
    
    @RequestMapping(value="/createMission", method = RequestMethod.POST)
    public ResponseEntity<String> createMission(@RequestBody Map<String, String> parameters){
        System.out.println(parameters.toString());
        missionRepository.save(Mission.builder().isCompleted( (parameters.get("isCompleted")) == null?false:(parameters.get("isCompleted")).equals("true"))
                                                .isDeleted(parameters.get("isDeleted") == null?false:parameters.get("isDeleted").equals("true"))
                                                .missionName((String) parameters.get("missionName"))
                                                .superHeroName((String) parameters.get("superHeroName"))
                                                .build()
                                               );
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
    public ResponseEntity<String> udpdateMission(@RequestParam Map<String, String> parameters){
        List<Mission> missions = missionRepository.findByMissionName( parameters.get("missionName"));
        for(Mission mission: missions){
            mission.setCompleted((parameters.get("_isCompleted")).equals("true"));
            mission.setDeleted((parameters.get("_isDeleted")).equals("true"));
            mission.setSuperHeroName( parameters.get("_superHeroName"));
            mission.setMissionName( parameters.get("_missionName"));
            missionRepository.save(mission);
        } 
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @RequestMapping(value="/deleteMission", method=RequestMethod.POST)
    public ResponseEntity<String> deleteMission(@RequestParam Map<String, String> parameters){
        List<Mission> missions = missionRepository.findByMissionName(parameters.get("missionName"));
        for(Mission mission: missions){
            missionRepository.delete(mission);
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
}
