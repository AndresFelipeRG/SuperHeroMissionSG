package com.interview.SuperHeroMissionSG.repository;

import com.interview.SuperHeroMissionSG.model.Mission;
import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {
}
