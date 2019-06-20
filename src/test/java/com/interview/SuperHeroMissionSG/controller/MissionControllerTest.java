package com.interview.SuperHeroMissionSG.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionControllerTest {
    
    @Autowired
    private MissionRepository repository;
    
    @Autowired
    private SuperHeroController heroController;
   
    @Autowired
    private MissionController controller;
    
    @Test
    public void createMissionTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        heroController.createSuperHero(parameters);
        List<Mission> missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        controller.deleteMission(parameters);
    }
    @Test
    public void emptyMissionTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        ResponseEntity<String> response = controller.createMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptyMissionName\": true}"));
        parameters = new HashMap<>();
        response = controller.createMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptyMissionName\": true}"));
    }
    @Test
    public void noDuplicatesTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        controller.createMission(parameters);
        controller.createMission(parameters);
        parameters.put("isCompleted", "true");
        controller.createMission(parameters);
        List<Mission> missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        controller.deleteMission(parameters);
    }
    @Test
    public void deleteAMissionThenAddItBackTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        List<Mission> missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        controller.deleteMission(parameters);
        missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(0));
        
        heroController.createSuperHero(parameters);
        missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        controller.deleteMission(parameters);
    }
    @Test
    public void addDeletedMissionTest(){
        Map<String, String> parametersH = new HashMap<>();
        parametersH.put("firstName", "andres");
        parametersH.put("lastName", "rincon");
        parametersH.put("missionName", "missionDelete");
        parametersH.put("superHeroName", "hero");
        parametersH.put("isCompleted", "false");
        parametersH.put("isDeleted", "false");
        heroController.createSuperHero(parametersH);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "true");
        controller.deleteMission(parameters);
        ResponseEntity<String> response = controller.createMission(parameters);
        List<Mission> missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(0));
        Assert.assertThat(response.getBody(), is("{\"missionAlreadyDeleted\": true}"));
        heroController.deleteSuperHero(parametersH);
    }
    @Test
    public void getAllMissionsTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        ResponseEntity<String> missions = controller.getAllMissions();
        JSONArray response = new JSONArray(missions.getBody());
        Assert.assertThat(response.toString(), not("[]"));
        JSONObject mission = new JSONObject(response.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertFalse((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));
        controller.deleteMission(parameters);
    }
    @Test
    public void getMissionByNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        ResponseEntity<String> missions = controller.getMissionByName(parameters);
        JSONArray response = new JSONArray(missions.getBody());
        Assert.assertThat(response.toString(), not("[]"));
        JSONObject mission = new JSONObject(response.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertFalse((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));
        controller.deleteMission(parameters);
    }
    @Test
    public void getMissionBySuperHeroNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        ResponseEntity<String> missions = controller.getMissionBySuperHeroName(parameters);
        JSONArray response = new JSONArray(missions.getBody());
        Assert.assertThat(response.toString(), not("[]"));
        JSONObject mission = new JSONObject(response.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertFalse((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));
        controller.deleteMission(parameters);
    }
    @Test
    public void updateMissionTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", "andres");
        parameters.put("lastName", "rincon");
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        ResponseEntity<String> missions = controller.getMissionByName(parameters);
        JSONArray response = new JSONArray(missions.getBody());
        Assert.assertThat(response.toString(), not("[]"));
        JSONObject mission = new JSONObject(response.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertFalse((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));
        parameters.put("_missionName", "missionA");
        parameters.put("_isCompleted", "true");
        parameters.put("_isDeleted", "false");
        controller.udpdateMission(parameters);
        parameters.put("missionName", "missionA");
        parameters.put("isCompleted", "true");
        parameters.put("isDeleted", "false");
        missions = controller.getMissionByName(parameters);
        response = new JSONArray(missions.getBody());
        Assert.assertThat(response.toString(), not("[]"));
        mission = new JSONObject(response.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("_missionName")));
        Assert.assertTrue((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));
        controller.deleteMission(parameters);
    }
    @Test
    public void updateMissionCheckNotNullsTest(){
        Map<String, String> parametersH = new HashMap<>();
        parametersH.put("firstName", "andres");
        parametersH.put("lastName", "rincon");
        parametersH.put("missionName", "missionDelete");
        parametersH.put("superHeroName", "hero");
        parametersH.put("isCompleted", "false");
        parametersH.put("isDeleted", "false");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        ResponseEntity<String> response = controller.createMission(parameters);
        parameters.put("_missionName", "");
        parameters.put("_isCompleted", "true");
        parameters.put("_isDeleted", "true");
        response = controller.udpdateMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptyNewMissionName\": true}"));
        parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        parameters.put("_missionName", "missionB");
        response = controller.udpdateMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"empty_isCompleted\": true}"));
        parameters.put("_isCompleted", "true");
        response = controller.udpdateMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"empty_isDeleted\": true}"));
        parameters.put("_isDeleted", "false");
        controller.udpdateMission(parameters);
        parameters.put("missionName", "missionB");
        controller.deleteMission(parameters);
        heroController.deleteSuperHero(parametersH);
    }
    @Test
    public void deleteMissionTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionD");
        parameters.put("superHeroName", "heroD");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        heroController.createSuperHero(parameters);
        List<Mission> missions = repository.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        controller.deleteMission(parameters);
        ResponseEntity<String> response = controller.getAllMissions();
        Assert.assertThat(response.getBody(), is("[]"));
    }
    @Test
    public void notNullMissionNameDeleteTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "");
        parameters.put("superHeroName", "heroD");
        parameters.put("isCompleted", "false");
        parameters.put("isDeleted", "false");
        ResponseEntity<String> response = controller.deleteMission(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptyMissionName\": true}"));    
    }
   
}
