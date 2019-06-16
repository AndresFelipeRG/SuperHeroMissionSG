package com.interview.SuperHeroMissionSG.controller;

import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
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
public class MissionController {
    
    @Autowired
    MissionRepository missionRepository;
    
    @RequestMapping(value="/createMission", method = RequestMethod.POST)
    public ResponseEntity<String> createMission(@RequestParam Map<String, Object> parameters){
        missionRepository.save(Mission.builder().isCompleted(Boolean.getBoolean((String) parameters.get("isCompleted")))
                                                .isDeleted(Boolean.getBoolean((String) parameters.get("isDeleted")))
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
            JSONObject missionJson = new JSONObject();
            missionJson.put("missionName", mission.getSuperHeroName());
            missionJson.put("superHeroName", mission.getSuperHeroName());
            missionJson.put("isCompleted", mission.isCompleted());
            missionJson.put("isDeleted", mission.isDeleted());
            missions.put(missionJson);
        }
        return new ResponseEntity<>(missions.toString(), HttpStatus.OK);
        
    }
}
