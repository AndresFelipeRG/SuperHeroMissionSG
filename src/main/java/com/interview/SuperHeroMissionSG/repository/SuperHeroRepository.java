package com.interview.SuperHeroMissionSG.repository;

import com.interview.SuperHeroMissionSG.model.SuperHero;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

    List<SuperHero> findBySuperHeroName(String superHeroName);
    List<SuperHero> findByMissionName(String missionName);
}
