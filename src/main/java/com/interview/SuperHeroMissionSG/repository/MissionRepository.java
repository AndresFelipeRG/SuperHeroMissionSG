package com.interview.SuperHeroMissionSG.repository;

import com.interview.SuperHeroMissionSG.model.Mission;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {
    
    List<Mission> findByMissionName(String missionName);
    List<Mission> findBySuperHeroName(String superHeroName);
    
}
