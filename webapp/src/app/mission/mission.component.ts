import { MissionService } from './mission.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Mission} from './mission';

@Component({
  selector: 'app-mission',
  templateUrl: './mission.component.html',
  styleUrls: ['./mission.component.css']
})
export class MissionComponent implements OnInit{
    missions: Mission[];
    selectedMission: Mission;
    constructor(private missionService: MissionService){ }

    ngOnInit(): void {
     this.getMissions();
    }
    onSelect(mission: Mission): void{
      this.selectedMission = mission;
    }
    details(mission: Mission): void{
      this.missionService.navigateToDetails(mission);
    }
    getMissions(): void {
      this.missionService.getAllMissions()
          .subscribe((missionData) => {
                this.missions = missionData
          })
    }
    deleteMission(mission: Mission):void{
      this.selectedMission = this.selectedMission !== mission ? this.selectedMission: null;
      this.missionService.deleteMission(mission).subscribe(
        result => this.missionService.navigateToMissions()
      );
    }

  }
