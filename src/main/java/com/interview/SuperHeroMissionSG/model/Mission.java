package com.interview.SuperHeroMissionSG.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
//@IdClass(MissionId.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE mission SET is_deleted  = true WHERE id=?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted <> true")
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
   // @Id
    @Column(name = "missionName")
    private String missionName;
    @Column(name = "superHeroName")
    private String superHeroName;
    @Column(name = "isCompleted")
    private boolean isCompleted;
    @Column(name = "isDeleted")
    private boolean isDeleted;
}
