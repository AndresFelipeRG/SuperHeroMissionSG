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
import com.interview.SuperHeroMissionSG.model.Mission;
import com.interview.SuperHeroMissionSG.model.SuperHero;
import com.interview.SuperHeroMissionSG.repository.MissionRepository;
import com.interview.SuperHeroMissionSG.repository.SuperHeroRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperHeroControllerTest {
    
    @Autowired
    private SuperHeroRepository repository;
   
    @Autowired
    private SuperHeroController controller;
    
    @Autowired
    private MissionController controllerM;
    
    @Autowired
    private MissionRepository repositoryM;
    
    @Test
    public void createHeroTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        List<SuperHero> heroes = repository.findBySuperHeroName(parameters.get("superHeroName"));
        Assert.assertThat(heroes.size(), is(1));
        Assert.assertThat(heroes.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(heroes.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertThat(heroes.get(0).getFirstName(), is(parameters.get("firstName")));
        Assert.assertThat(heroes.get(0).getLastName(), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void checkForDuplicatesTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        controller.createSuperHero(parameters);
        controller.createSuperHero(parameters);
        List<SuperHero> heroes = repository.findBySuperHeroName(parameters.get("superHeroName"));
        Assert.assertThat(heroes.size(), is(1));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void checkNotNullSuperHeroNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        ResponseEntity<String> response =  controller.createSuperHero(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptySuperHeroName\": true}"));
        parameters = new HashMap<>();
        response =  controller.createSuperHero(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptySuperHeroName\": true}"));   
    }
    
    @Test
    public void updateHeroTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        parameters.put("_missionName", "missionD");
        parameters.put("_superHeroName", "heroD");
        parameters.put("_firstName", "");
        parameters.put("_lastName", "");
        controller.udpdateSuperHero(parameters);
        parameters.put("missionName", "missionD");
        parameters.put("superHeroName", "heroD");
        parameters.put("firstName", "");
        parameters.put("lastName", "");
        List<SuperHero> heroes = repository.findBySuperHeroName(parameters.get("superHeroName"));
        Assert.assertThat(heroes.size(), is(1));
        Assert.assertThat(heroes.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(heroes.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertThat(heroes.get(0).getFirstName(), is(parameters.get("firstName")));
        Assert.assertThat(heroes.get(0).getLastName(), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void checkNotEmptySuperHeroNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        parameters.put("_missionName", "missionD");
        parameters.put("_superHeroName", "");
        parameters.put("_firstName", "");
        parameters.put("_lastName", "");
        ResponseEntity<String> response = controller.udpdateSuperHero(parameters);
        Assert.assertThat(response.getBody(), is("{\"emptySuperHeroName\": true}"));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void getAllSuperHeroesTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionF");
        parameters.put("superHeroName", "heroF");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        JSONArray heroes = new JSONArray(controller.getAllSuperHeroes().getBody());
        JSONObject hero = new JSONObject(heroes.get(0).toString());
        Assert.assertThat(hero.getString("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(hero.getString("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertThat(hero.getString("firstName"), is(parameters.get("firstName")));
        Assert.assertThat(hero.getString("lastName"), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void getAllSuperHeroesByNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        JSONArray heroes = new JSONArray(controller.getSuperHeroByName(parameters).getBody());
        JSONObject hero = new JSONObject(heroes.get(0).toString());
        Assert.assertThat(hero.getString("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(hero.getString("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertThat(hero.getString("firstName"), is(parameters.get("firstName")));
        Assert.assertThat(hero.getString("lastName"), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void getAllSuperHeroesMissionNameTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        JSONArray heroes = new JSONArray(controller.getSuperHeroByMissionName(parameters).getBody());
        JSONObject hero = new JSONObject(heroes.get(0).toString());
        Assert.assertThat(hero.getString("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(hero.getString("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertThat(hero.getString("firstName"), is(parameters.get("firstName")));
        Assert.assertThat(hero.getString("lastName"), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
    }
    @Test
    public void getActiveMissionsTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        
        Map<String, String> parametersM = new HashMap<>();
        parametersM.put("missionName", parameters.get("missionName"));
        parametersM.put("superHeroName", parameters.get("superHeroName"));
        parametersM.put("isCompleted", "false");
        parametersM.put("isDeleted", "false");
        controllerM.createMission(parametersM);
        
        List<Mission> missions = repositoryM.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertFalse(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        
        
        JSONArray missionsJ = new JSONArray(controller.getActiveMissions(parameters).getBody());
        JSONObject mission = new JSONObject(missionsJ.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertFalse((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));

        controller.deleteSuperHero(parameters);
        controllerM.deleteMission(parametersM);
    }
    @Test
    public void getCompletedMissions(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "missionA");
        parameters.put("superHeroName", "heroB");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        
        Map<String, String> parametersM = new HashMap<>();
        parametersM.put("missionName", parameters.get("missionName"));
        parametersM.put("superHeroName", parameters.get("superHeroName"));
        parametersM.put("isCompleted", "true");
        parametersM.put("isDeleted", "false");
        controllerM.createMission(parametersM);
        
        List<Mission> missions = repositoryM.findByMissionName(parameters.get("missionName"));
        Assert.assertThat(missions.size(), is(1));
        Assert.assertThat(missions.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(missions.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertTrue(missions.get(0).isCompleted());
        Assert.assertFalse(missions.get(0).isDeleted());
        
        
        JSONArray missionsJ = new JSONArray(controller.getCompletedMissions(parameters).getBody());
        JSONObject mission = new JSONObject(missionsJ.get(0).toString());
        Assert.assertThat(mission.get("missionName"), is(parameters.get("missionName")));
        Assert.assertThat(mission.get("superHeroName"), is(parameters.get("superHeroName")));
        Assert.assertTrue((boolean) mission.get("isCompleted"));
        Assert.assertFalse((boolean) mission.get("isDeleted"));

        controller.deleteSuperHero(parameters);
        controllerM.deleteMission(parametersM);
    }
    @Test
    public void deleteSuperHeroTest(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("missionName", "mission");
        parameters.put("superHeroName", "hero");
        parameters.put("firstName", "false");
        parameters.put("lastName", "false");
        controller.createSuperHero(parameters);
        List<SuperHero> heroes = repository.findBySuperHeroName(parameters.get("superHeroName"));
        Assert.assertThat(heroes.size(), is(1));
        Assert.assertThat(heroes.get(0).getMissionName(), is(parameters.get("missionName")));
        Assert.assertThat(heroes.get(0).getSuperHeroName(), is(parameters.get("superHeroName")));
        Assert.assertThat(heroes.get(0).getFirstName(), is(parameters.get("firstName")));
        Assert.assertThat(heroes.get(0).getLastName(), is(parameters.get("lastName")));
        controller.deleteSuperHero(parameters);
        heroes = repository.findBySuperHeroName(parameters.get("superHeroName"));
        Assert.assertThat(heroes.size(), is(0));
    }
}
