package com.interview.SuperHeroMissionSG.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mission")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@SQLDelete(sql = "UPDATE missions SET isDeleted = true WHERE id=?", check = ResultCheckStyle.COUNT)
@Where(clause = "isDeleted <> true")
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String missionName;
    private String superHeroName;
    private boolean isCompleted;
    private boolean isDeleted;
}
