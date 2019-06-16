package com.interview.SuperHeroMissionSG.controller;

import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
import java.util.Map;
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
    public ResponseEntity<String> createMission(@RequestParam Map<String, String> parameters){
        missionRepository.save(Mission.builder().isCompleted(parameters.get("isCompleted").equals("true"))
                                                .isDeleted(parameters.get("isDeleted").equals("true"))
                                                .missionName(parameters.get("missionName"))
                                                .superHeroName(parameters.get("superHeroName"))
                                                .build()
                                               );
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
